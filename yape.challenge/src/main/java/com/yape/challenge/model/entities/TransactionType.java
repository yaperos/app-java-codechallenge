package com.yape.challenge.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="transaction_type")
@Data
public class TransactionType {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    public TransactionType() {}

    public TransactionType(Long id) {
        this.id = id;
    }


}
