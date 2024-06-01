package com.yape.transaction.manager.application.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class FindTransactionResponse {

    String transactionExternalId;
    TransactionType transactionType;
    TransactionStatus transactionStatus;
    double value;
    Date createAt;
}
