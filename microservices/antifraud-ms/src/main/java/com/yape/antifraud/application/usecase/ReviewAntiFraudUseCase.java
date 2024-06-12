package com.yape.antifraud.application.usecase;

import com.yape.antifraud.application.port.in.ReviewAntiFraudCommand;
import com.yape.antifraud.application.port.out.TransactionEdaRepository;
import com.yape.antifraud.domain.TransactionStatusEnum;
import com.yape.antifraud.infra.out.adapter.kafka.model.TransactionOutModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class ReviewAntiFraudUseCase implements ReviewAntiFraudCommand {

    @Value("${antifraud.transaction.max.value}")
    private BigDecimal maxValue;
    private final TransactionEdaRepository transactionEdaRepository;

    public ReviewAntiFraudUseCase(TransactionEdaRepository transactionEdaRepository) {
        this.transactionEdaRepository = transactionEdaRepository;
    }

    @Override
    public void execute(Command command) {
        log.info("Reviewing transaction id: {}", command.getId());
        TransactionOutModel transactionModel = TransactionOutModel.builder()
                .id(command.getId())
                .status(command.getStatus())
                .code(command.getCode())
                .build();
        transactionModel.setStatus(TransactionStatusEnum.APPROVED);
        if (command.getValue().compareTo(maxValue) > 0) {
            transactionModel.setStatus(TransactionStatusEnum.REJECTED);
        }
        this.transactionEdaRepository.updateTransaction(transactionModel);
    }
}
