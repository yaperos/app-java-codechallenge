package com.pe.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 


/**
 * Class: TransactionEntity.
 * @author Tiva
 */

@Table(name = "Transaction")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private String transactionExternalId;
    private String transactionType;
    private String transactionStatus;
    private int value;
    private String createdAt;

}