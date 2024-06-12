package com.yape.transaction.application.usecase;

import com.yape.transaction.application.port.in.RegisterTransactionCommand;
import com.yape.transaction.application.port.out.AntiFraudEdaRepository;
import com.yape.transaction.application.port.out.CacheRepository;
import com.yape.transaction.application.port.out.TransactionRepository;
import com.yape.transaction.domain.Transaction;
import com.yape.transaction.domain.type.TransactionStatusEnum;
import com.yape.transaction.domain.type.TransactionTypeEnum;
import com.yape.transaction.domain.type.TransferTypeEnum;
import com.yape.transaction.infra.out.adapter.data.model.TransactionDataModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class RegisterTransactionUseCase implements RegisterTransactionCommand {
    private final TransactionRepository repository;
    private final AntiFraudEdaRepository antiFraudEdaRepository;
    private final CacheRepository cacheRepository;

    public RegisterTransactionUseCase(TransactionRepository repository, AntiFraudEdaRepository antiFraudEdaRepository, CacheRepository cacheRepository) {
        this.repository = repository;
        this.antiFraudEdaRepository = antiFraudEdaRepository;
        this.cacheRepository = cacheRepository;
    }

    @Override
    public Transaction execute(Command command) {
        TransactionDataModel entity = TransactionDataModel.builder()
                .code(UUID.randomUUID())
                .accountExternalIdCredit(command.getAccountExternalIdCredit())
                .accountExternalIdDebit(command.getAccountExternalIdDebit())
                .value(command.getValue())
                .transferType(TransferTypeEnum.findByValue(command.getTransferTypeId()).name())
                .status(TransactionStatusEnum.PENDING.name())
                .type( TransactionTypeEnum.TRANSFER.name())
                .createAt(LocalDateTime.now())
                .build();
       Transaction transaction = this.repository.save(entity).toDomain();
       this.cacheRepository.saveTransaction(transaction);
       this.antiFraudEdaRepository.validate(transaction);

       return transaction;
    }
}
