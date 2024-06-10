package com.yape.codechallenge.transactionmanagementservice.infra.inputport;

import java.math.BigDecimal;

public interface ValidatorInputPort {
    void validateTransactionInputs(String accountExternalIdDebit, String accountExternalIdCredit, int transferTypeId, BigDecimal value);
}
