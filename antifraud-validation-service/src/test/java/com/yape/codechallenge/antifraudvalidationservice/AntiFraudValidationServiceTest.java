package com.yape.codechallenge.antifraudvalidationservice;

import com.yape.codechallenge.antifraudvalidationservice.event.IncomingCreationEvent;
import com.yape.codechallenge.antifraudvalidationservice.event.OutComingUpdatingEvent;
import com.yape.codechallenge.antifraudvalidationservice.service.AntiFraudValidationService;
import com.yape.codechallenge.antifraudvalidationservice.util.ConstantsUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AntiFraudValidationServiceTest {

    private AntiFraudValidationService antiFraudValidationService;

    @BeforeEach
    void setUp() {
        antiFraudValidationService = new AntiFraudValidationService();
    }

    @Test
    @DisplayName("Should return accepted status for valid transaction")
    void shouldReturnAcceptedStatusForValidTransaction() {
        IncomingCreationEvent incomingCreationEvent = new IncomingCreationEvent();
        incomingCreationEvent.setTransactionExternalId("TX123");
        incomingCreationEvent.setOperationType(ConstantsUtils.TRANSACTION_DB_OPERATION_CREATE);
        incomingCreationEvent.setTransactionReceiptValue(new BigDecimal(500));

        OutComingUpdatingEvent result = antiFraudValidationService.evaluateTransactionFraudRisk(incomingCreationEvent);

        assertEquals(ConstantsUtils.TRANSACTION_STATUS_ACCEPTED, result.getEvaluationResult());
    }

    @Test
    @DisplayName("Should return rejected status for high value transaction")
    void shouldReturnRejectedStatusForHighValueTransaction() {
        IncomingCreationEvent incomingCreationEvent = new IncomingCreationEvent();
        incomingCreationEvent.setTransactionExternalId("TX123");
        incomingCreationEvent.setOperationType(ConstantsUtils.TRANSACTION_DB_OPERATION_CREATE);
        incomingCreationEvent.setTransactionReceiptValue(new BigDecimal(1500));

        OutComingUpdatingEvent result = antiFraudValidationService.evaluateTransactionFraudRisk(incomingCreationEvent);

        assertEquals(ConstantsUtils.TRANSACTION_STATUS_REJECTED, result.getEvaluationResult());
    }

    @Test
    @DisplayName("Should return rejected status for non-create operation")
    void shouldReturnRejectedStatusForNonCreateOperation() {
        IncomingCreationEvent incomingCreationEvent = new IncomingCreationEvent();
        incomingCreationEvent.setTransactionExternalId("TX123");
        incomingCreationEvent.setOperationType("update");
        incomingCreationEvent.setTransactionReceiptValue(new BigDecimal(500));

        OutComingUpdatingEvent result = antiFraudValidationService.evaluateTransactionFraudRisk(incomingCreationEvent);

        assertEquals(ConstantsUtils.TRANSACTION_STATUS_REJECTED, result.getEvaluationResult());
    }
}