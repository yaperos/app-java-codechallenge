package com.aly.transactions_service.model.entities;

import java.util.Date;

/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;*/

import com.fasterxml.jackson.annotation.JsonFormat;
import com.aly.transactions_service.util.Constant;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transaction_external")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionExternal {

	@Id
	@Column(name = "transaction_external_id", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "transaction_type_id", nullable = false)
	private TransactionType transactionType;

	@ManyToOne
	@JoinColumn(name = "transaction_status_id", nullable = false)
	private TransactionStatus transactionStatus;

	@Column(name = "VALUE")
	private Integer value;

	@Column(name = "CREATION_USER")
	private String userCreation;

	@Column(name = "CREATION_DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.FORMATO_FECHA_HORA, timezone = Constant.ZONA_HORARIA)
	private Date dateCreation;

	@Column(name = "UPDATE_USER")
	private String userUpdate;
	
	@Column(name = "UPDATE_DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.FORMATO_FECHA_HORA, timezone = Constant.ZONA_HORARIA)
	private Date dateUpdate;

}
