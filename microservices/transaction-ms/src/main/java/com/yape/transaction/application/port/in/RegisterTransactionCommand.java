package com.yape.transaction.application.port.in;

import com.yape.transaction.domain.Transaction;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

public interface RegisterTransactionCommand {
    Transaction execute(Command command);
    @Value
    @Builder
    class Command {
        UUID accountExternalIdDebit;
        UUID accountExternalIdCredit;
        Integer transferTypeId;
        BigDecimal value;
    }

}
