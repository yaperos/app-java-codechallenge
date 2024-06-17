package com.yape.transaction.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "transactionExternalId")
public class YapeTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID transactionExternalId;

    @Column(name = "account_external_id_debit")
    private UUID accountExternalIdDebit;

    @Column(name = "account_external_id_credit")
    private UUID accountExternalIdCredit;

    @ManyToOne
    @JoinColumn(name = "transfer_type_id", nullable = false)
    private TransactionType transactionType;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private TransactionStatus transactionStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private String createdAt;
    
    @AssertTrue(message = "Transaction must either come from debit or credit card")
    private boolean isTransactionValid() {
        return accountExternalIdDebit != null || accountExternalIdCredit != null;
    }
}