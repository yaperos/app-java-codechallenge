package com.aly.transactions_service.model.entities;

/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;*/

import jakarta.persistence.*;
import lombok.*;

/*@Data
@Entity
@Table(name = "transaction_type")
@Builder*/

@Entity
@Table(name = "transaction_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionType {

	@Id
	@Column(name = "transaction_type_id", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

}
