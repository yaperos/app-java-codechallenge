package com.yape.challenge.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.util.Date;

@Entity
@Table(name="transfers")
@Data
public class Transfer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="account_debit")
    private String accountDebit;

    @Column(name="account_credit")
    private String accountCredit;

    @ManyToOne(targetEntity = TransactionType.class)
    private TransactionType transactionType;

    private double amount;

    @ManyToOne(targetEntity = TransactionStatus.class)
    private TransactionStatus transactionStatus;

    @Column(name = "created_at")
    @Temporal(jakarta.persistence.TemporalType.TIMESTAMP)
    private Date createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }


}
