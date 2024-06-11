package com.yape.codechallenge.transactionmanagementservice.infra.outputadapter.postgresrepository;

import com.yape.codechallenge.transactionmanagementservice.domain.TransactionStatus;
import com.yape.codechallenge.transactionmanagementservice.domain.TransactionType;
import com.yape.codechallenge.transactionmanagementservice.infra.outputport.CommandRepository;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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
                        log.error("Error saving entity", e);
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
            Object row = createBuilderInstance();
            Method[] methods = row.getClass().getDeclaredMethods();
            for (Method method : methods) {
                String columnName = method.getName();
                if (isColumnPresent(rs, columnName)) {
                    Object fieldValue = getFieldValue(rs, method, columnName);
                    method.invoke(row, fieldValue);
                }
            }
            return buildRow(row);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException e) { log.error("Error mapping row", e); }
        return null;
    }

    private Object createBuilderInstance() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method builderMethod = clazz.getMethod("builder");
        return builderMethod.invoke(null);
    }

    private boolean isColumnPresent(ResultSet rs, String columnName) {
        try {
            rs.findColumn(columnName);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    private Object getFieldValue(ResultSet rs, Method method, String columnName) throws SQLException {
        Object fieldValue = rs.getObject(columnName);
        Class<?>[] parameterTypes = method.getParameterTypes();

        if (parameterTypes.length > 0) {
            if (parameterTypes[0].isEnum()) {
                fieldValue = Enum.valueOf((Class<Enum>) parameterTypes[0], (String) fieldValue);
            } else if (parameterTypes[0].equals(LocalDateTime.class)) {
                fieldValue = convertToDateTime(rs, columnName);
            }
        }
        return fieldValue;
    }

    private LocalDateTime convertToDateTime(ResultSet rs, String columnName) throws SQLException {
        Timestamp timestamp = rs.getTimestamp(columnName);
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }

    private T buildRow(Object row) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return (T) row.getClass().getMethod("build").invoke(row);
    }
    }
}
