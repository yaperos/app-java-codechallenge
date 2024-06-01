package com.yape.transaction.manager.infrastructure.repository;

import com.yape.transaction.manager.application.response.FindTransactionResponse;
import com.yape.transaction.manager.application.response.TransactionStatus;
import com.yape.transaction.manager.application.response.TransactionType;
import com.yape.transaction.manager.domain.Transaction;
import com.yape.transaction.manager.domain.contants.TransactionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Table(value = "transaction")
public class TransactionEntity {

    @Id
    @PrimaryKeyColumn(name = "transaction_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID id;

    @Indexed
    @Column("external_id")
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID externalId;

    @Column("type")
    @CassandraType(type = CassandraType.Name.TEXT)
    private int transferTypeId;

    @Column("status")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String status;

    @Column("value")
    @CassandraType(type = CassandraType.Name.DOUBLE)
    private double value;

    @Column("created_date")
    @CassandraType(type = CassandraType.Name.TIMESTAMP)
    private Date createdAt;

    public TransactionEntity(Transaction transaction){

        if(transaction.getInternalId() != null){
            this.id = UUID.fromString(transaction.getInternalId());
        }
        else {
            this.id = UUID.randomUUID();
        }

        this.externalId = UUID.fromString(transaction.getTransactionExternalId());
        this.transferTypeId = transaction.getTransferTypeId();
        this.status = transaction.getStatus();
        this.value = transaction.getValue();
        this.createdAt = transaction.getCreatedAt();
    }

    Transaction toDomainTransaction() {
        return new Transaction(id.toString(), externalId.toString(), transferTypeId, status, value, createdAt);
    }
}