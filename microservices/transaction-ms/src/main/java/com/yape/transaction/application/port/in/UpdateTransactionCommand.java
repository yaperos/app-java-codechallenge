package com.yape.transaction.application.port.in;

import com.yape.transaction.domain.type.TransactionStatusEnum;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

public interface UpdateTransactionCommand {
    void  execute(Command command);
    @Value
    @Builder
    class Command {
        Long id;
        UUID code;
        TransactionStatusEnum status;
    }

}
