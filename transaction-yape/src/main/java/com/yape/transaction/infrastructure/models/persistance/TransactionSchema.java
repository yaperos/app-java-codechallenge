package com.yape.transaction.infrastructure.models.persistance;

import com.yape.transaction.entities.enums.TransactionStatus;
import com.yape.transaction.entities.enums.TransactionType;
import com.yape.transaction.entities.enums.TransferType;
import com.yape.transaction.entities.models.Transaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "transaction")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionSchema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="transaction_external_id")
    private UUID transactionExternalId;

    @Column(name = "account_external_id_credit")
    private UUID accountExternalIdCredit;

    @Column(name = "account_external_id_debit")
    private UUID accountExternalIdDebit;

    @Column(name = "transaction_status")
    private String transactionStatus;

    @Column(name = "transaction_type")
    private String transactionType;

    private Double value;

    @Column(name = "transfer_type")
    private String transferType;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    public static TransactionSchema convertFrom(Transaction transaction) {
        return TransactionSchema.builder()
                .id(transaction.getId())
                .accountExternalIdCredit(transaction.getAccountExternalIdCredit())
                .accountExternalIdDebit(transaction.getAccountExternalIdDebit())
                .transactionExternalId(transaction.getTransactionExternalId())
                .transactionStatus(transaction.getTransactionStatus() == null ? null : transaction.getTransactionStatus().name())
                .transactionType(transaction.getTransactionType() == null ? null : transaction.getTransactionType().name())
                .value(transaction.getValue())
                .transferType(transaction.getTransferType() == null ? null : transaction.getTransferType().name())
                .createAt(transaction.getCreateAt())
                .updateAt(transaction.getUpdateAt())
                .build();
    }

    public Transaction getEntity() {
        return Transaction.builder()
                .id(this.getId())
                .accountExternalIdCredit(this.getAccountExternalIdCredit())
                .transactionExternalId(this.getTransactionExternalId())
                .accountExternalIdDebit(this.getAccountExternalIdDebit())
                .transactionStatus(this.getTransactionStatus() == null ? null : TransactionStatus.valueOf(this.getTransactionStatus()))
                .value(this.getValue())
                .transactionType(this.getTransactionType() == null ? null : TransactionType.valueOf(this.getTransactionType()))
                .transferType(this.getTransferType() == null ? null : TransferType.valueOf(this.getTransferType()))
                .createAt(this.getCreateAt())
                .build();
    }
}
