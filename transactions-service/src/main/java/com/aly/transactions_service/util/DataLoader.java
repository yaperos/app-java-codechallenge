package com.aly.transactions_service.util;

import com.aly.transactions_service.model.entities.TransactionStatus;
import com.aly.transactions_service.model.entities.TransactionType;
import com.aly.transactions_service.repositories.InterfaceTransactionStatusDao;
import com.aly.transactions_service.repositories.InterfaceTransactionTypeDao;
import com.aly.transactions_service.services.InterfaceTransactionExternalService;
import com.aly.transactions_service.services.InterfaceTransactionStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    @Autowired
    InterfaceTransactionStatusDao interfaceTransactionStatusDao;
    @Autowired
    InterfaceTransactionTypeDao interfaceTransactionTypeDao;

    @Override
    public void run(String... args) throws Exception {
        log.info("Loading data.............");
        log.info("size type: " + interfaceTransactionTypeDao.findAll().size());
        log.info("size: status" + interfaceTransactionStatusDao.findAll().size());
        if (interfaceTransactionTypeDao.findAll().size() == 0) {
            interfaceTransactionTypeDao.saveAll(
                    List.of(
                            TransactionType.builder().code("1").name("Transfers").build(),
                            TransactionType.builder().code("2").name("Payments").build(),
                            TransactionType.builder().code("3").name("Shopping").build()
                    )
            );
        }
        if (interfaceTransactionStatusDao.findAll().size() == 0) {
            interfaceTransactionStatusDao.saveAll(
                    List.of(
                            TransactionStatus.builder().code("1").name("Pending").build(),
                            TransactionStatus.builder().code("2").name("Approved").build(),
                            TransactionStatus.builder().code("3").name("Rejected").build()
                    )
            );
        }
        log.info("Data loaded........");
    }
}
