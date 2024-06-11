package com.aly.transactions_service.model.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transaction_status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionStatus {

	@Id
	@Column(name = "transaction_status_id", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

}
