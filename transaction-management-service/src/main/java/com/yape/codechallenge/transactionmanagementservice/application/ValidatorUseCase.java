package com.yape.codechallenge.transactionmanagementservice.application;

import com.yape.codechallenge.transactionmanagementservice.infra.inputport.ValidatorInputPort;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class ValidatorUseCase implements ValidatorInputPort {
    private static final String IBAN_REGEX = "^PE[0-9]{2}[a-zA-Z0-9]{20}$";

    @Override
    public void validateTransactionInputs(String accountExternalIdDebit, String accountExternalIdCredit, int transferTypeId, BigDecimal value) {
        validateIban(accountExternalIdDebit);
        validateIban(accountExternalIdCredit);
        validateDuplicateIban(accountExternalIdDebit, accountExternalIdCredit);
        validateTransferTypeId(transferTypeId);
        validateValue(value);
    }

    private void validateIban(String accountId) {
        if (!accountId.matches(IBAN_REGEX)) {
            throw new IllegalArgumentException("Account External Id does not Match IBAN Standard: " + accountId);
        }
    }

    private void validateDuplicateIban(String accountIdDebit, String accountIdCredit) {
        if (accountIdDebit.equals(accountIdCredit)) {
            throw new IllegalArgumentException("Account External Id Debit and Credit are the same: " + accountIdDebit);
        }
    }

    private void validateTransferTypeId(int transferTypeId) {
        if (transferTypeId <= 0) {
            throw new IllegalArgumentException("Invalid transferTypeId: " + transferTypeId);
        }
    }

    private void validateValue(BigDecimal value) {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid value: " + value);
        }
    }
}
