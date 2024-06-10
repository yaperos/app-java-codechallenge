package com.yape.codechallenge.transactionmanagementservice.infra.outputadapter.postgresrepository;

import com.yape.codechallenge.transactionmanagementservice.domain.TransactionStatus;
import com.yape.codechallenge.transactionmanagementservice.domain.TransactionType;
import com.yape.codechallenge.transactionmanagementservice.infra.outputport.CommandRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Component
public class PostgresRepository implements CommandRepository {

    final
    JdbcTemplate jdbcTemplate;

    public PostgresRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public <T> T save(T reg) {
        Field[] entityFields = reg.getClass().getDeclaredFields();

        List<String> fields;
        List<String> list = new ArrayList<>();
        for (Field entityField : entityFields) {
            String name = entityField.getName();
            list.add(name);
        }
        fields = list;

        List<Object> fieldValues = Stream.of(entityFields)
                .map(field -> {
                    try {
                        field.setAccessible(true);
                        Object value = field.get(reg);

                        if (value instanceof TransactionStatus || value instanceof TransactionType) {
                            value = value.toString();
                        }

                        return value;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .toList();

        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)",
                reg.getClass().getSimpleName(),
                String.join(",", fields),
                String.join(",", Collections.nCopies(fields.size(), "?")));

        jdbcTemplate.update(sql, fieldValues.toArray());

        return reg;
    }

    @Override
    public <T> T getById(String id, Class<T> clazz) {
        String sql = "SELECT * FROM " + clazz.getSimpleName() + " WHERE transactionexternalid = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new LombokRowMapper<T>(clazz), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static class LombokRowMapper<T> implements RowMapper<T> {
        private Class<?> clazz = null;

        public LombokRowMapper(Class<?> clazz) {
            this.clazz = clazz;
        }


        @Override
        public T mapRow(ResultSet rs, int rowNum) throws SQLException {
            try {
                Method builderMethod = clazz.getMethod("builder");
                Object row = builderMethod.invoke(null);
                Method[] methods = row.getClass().getDeclaredMethods();

                for (Method method : methods) {
                    int pos = -1;
                    try {
                        pos = rs.findColumn(method.getName());
                    } catch (SQLException ex) {
                    }

                    if (pos != -1) {
                        Object fieldValue = rs.getObject(pos);

                        // Check if the field is an enum
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        if (parameterTypes.length > 0 && parameterTypes[0].isEnum()) {
                            // Convert the string to an enum
                            fieldValue = Enum.valueOf((Class<Enum>) parameterTypes[0], (String) fieldValue);
                        }

                        // Check if the field is a LocalDateTime
                        if (parameterTypes.length > 0 && parameterTypes[0].equals(LocalDateTime.class)) {
                            // Convert the Timestamp to LocalDateTime
                            Timestamp timestamp = rs.getTimestamp(pos);
                            if (timestamp != null) {
                                fieldValue = timestamp.toLocalDateTime();
                            }
                        }

                        method.invoke(row, fieldValue);
                    }
                }

                return (T) row.getClass().getMethod("build").invoke(row);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                     | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            }

            return null;
        }

    }
}
