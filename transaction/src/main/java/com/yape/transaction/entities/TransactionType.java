package com.yape.transaction.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction_types")
@Data
@NoArgsConstructor
public class TransactionType {
    @Id
    private int id;

    @Column(name = "name", nullable = false)
    private String name;
}
