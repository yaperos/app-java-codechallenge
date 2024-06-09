package com.yape.codechallenge.model;

import java.util.UUID;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaccion")
public class Transaction {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "account_external_id_debit")
    private UUID accountExternalIdDebit;
    @Column(name = "account_external_id_credit")
    private UUID accountExternalIdCredit;
    @Column(name = "transfer_type_id")
    private int transferTypeId;
    @Column(name = "value")
    private BigDecimal value;
    @Column(name = "transaction_status")
    private String transactionStatus;
    @Column(name = "transaction_type")
    private String transactionType;
    @Column(name = "created_at")
    private String createdAt;
}
