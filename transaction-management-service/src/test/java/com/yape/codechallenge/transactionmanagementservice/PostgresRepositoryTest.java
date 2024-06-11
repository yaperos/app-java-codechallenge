package com.yape.codechallenge.transactionmanagementservice;

import com.yape.codechallenge.transactionmanagementservice.domain.TransactionStatus;
import com.yape.codechallenge.transactionmanagementservice.domain.TransactionType;
import com.yape.codechallenge.transactionmanagementservice.domain.Transactions;
import com.yape.codechallenge.transactionmanagementservice.infra.outputadapter.postgresrepository.PostgresRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PostgresRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    private PostgresRepository postgresRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        postgresRepository = new PostgresRepository(jdbcTemplate);
    }

    @Test
    void shouldSaveSuccessfully() {
        Transactions mockTransaction = Transactions.builder()
                .transactionExternalId("TX1234567890")
                .transactionStatus(TransactionStatus.valueOf("PENDING"))
                .transactionType(TransactionType.valueOf("TRANSFER"))
                .value(BigDecimal.valueOf(100))
                .createdAt(LocalDateTime.now())
                .build();

        postgresRepository.save(mockTransaction);

        verify(jdbcTemplate).update(anyString(), any(Object[].class));
    }

    @Test
    void shouldGetByIdSuccessfully() {
        Transactions mockTransaction = Transactions.builder()
                .transactionExternalId("TX1234567890")
                .transactionStatus(TransactionStatus.valueOf("PENDING"))
                .transactionType(TransactionType.valueOf("TRANSFER"))
                .value(BigDecimal.valueOf(100))
                .createdAt(LocalDateTime.now())
                .build();

        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyString())).thenReturn(mockTransaction);
        Transactions result = postgresRepository.getById("TX1234567890", Transactions.class);
        assertEquals("TX1234567890", result.getTransactionExternalId());
        verify(jdbcTemplate).queryForObject(anyString(), (RowMapper<Object>) any(), anyString());
    }

    @Test
    void shouldReturnNullWhenGetByIdFails() {
        String id = "TX1234567890";

        when(jdbcTemplate.queryForObject(anyString(), (RowMapper<Object>) any(), anyString())).thenThrow(EmptyResultDataAccessException.class);

        Transactions result = postgresRepository.getById(id, Transactions.class);

        assertNull(result);
        verify(jdbcTemplate).queryForObject(anyString(), (RowMapper<Object>) any(), anyString());
    }
}
