package com.yape.transactionmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transaction")
@Table(name = "transaction", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name ="code")
    private String transactionCode;

    @Column(name ="account_external_id_debit")
    private String accountExternalIdDebit;

    @Column(name ="account_external_id_credit")
    private String accountExternalIdCredit;

    @Column(name ="transfer_type")
    private String transactionType;

    @Column(name ="value")
    private BigDecimal transactionValue;

    @Column(name ="status")
    private String transactionStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
