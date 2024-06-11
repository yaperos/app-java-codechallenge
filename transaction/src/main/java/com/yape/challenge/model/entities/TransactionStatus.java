package com.yape.challenge.model.entities;

import com.yape.challenge.model.enums.TransferStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="transaction_status")
@Data
public class TransactionStatus {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    public TransactionStatus() {}

    public TransactionStatus(Long id) {
        this.id = id;
    }


}
