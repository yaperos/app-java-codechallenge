package com.yape.antifraud.application.port.in;

import com.yape.antifraud.domain.TransactionStatusEnum;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

public interface ReviewAntiFraudCommand {
    void  execute(Command command);
    @Value
    @Builder
    class Command {
        Long id;
        UUID code;
        TransactionStatusEnum status;
        BigDecimal value;
    }

}
