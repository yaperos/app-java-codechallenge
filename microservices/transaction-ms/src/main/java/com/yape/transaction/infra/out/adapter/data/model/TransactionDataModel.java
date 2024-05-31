package com.yape.transaction.infra.out.adapter.data.model;

import com.yape.transaction.domain.Transaction;
import com.yape.transaction.domain.type.TransactionStatusEnum;
import com.yape.transaction.domain.type.TransactionTypeEnum;
import com.yape.transaction.domain.type.TransferTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Entity(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDataModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="code")
    private UUID code;
    @Column(name = "account_external_id_debit")
    private UUID accountExternalIdDebit;
    @Column(name = "account_external_id_credit")
    private UUID accountExternalIdCredit;
    private String transferType;
    private String type;
    private String status;
    private BigDecimal value;
    @Column(name = "created_at")
    private LocalDateTime createAt;
    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    public Transaction toDomain() {
        return Transaction.builder()
                .id(id)
                .code(code)
                .accountExternalIdDebit(accountExternalIdDebit)
                .accountExternalIdCredit(accountExternalIdCredit)
                .transferType(TransferTypeEnum.valueOf(transferType))
                .type(TransactionTypeEnum.valueOf(type))
                .status(TransactionStatusEnum.valueOf(status))
                .value(value)
                .createAt(createAt)
                .updateAt(updateAt)
                .build();
    }
}
