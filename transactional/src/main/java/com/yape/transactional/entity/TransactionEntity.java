package com.yape.transactional.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "transaction")
@Data
public class TransactionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    @SequenceGenerator(name = "transaction_seq", sequenceName = "transaction_seq", allocationSize = 1)
    private Long id;
    @Column(name = "accountexternaliddebit")
    private String accountExternalIdDebit;
    @Column(name = "accountexternalidcredit")
    private String accountExternalIdCredit;
    @Column(name = "tranfertypeid")
    private Integer tranferTypeId;
    @Column(name = "value")
    private Double value;
    @Column(name = "transactionexternalid")
    private String  transactionExternalId;
    @Column(name = "transactiontype")
    private Integer transactionType;
    @Column(name = "transactionstatus")
    private Integer transactionStatus;
    @Column(name = "createdat")
    private Timestamp createdAt;

}
