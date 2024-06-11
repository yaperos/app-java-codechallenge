package com.yape.codechallenge.antifraudvalidationservice;

import com.yape.codechallenge.antifraudvalidationservice.event.IncomingCreationEvent;
import com.yape.codechallenge.antifraudvalidationservice.event.OutComingUpdatingEvent;
import com.yape.codechallenge.antifraudvalidationservice.listener.TransactionEventListener;
import com.yape.codechallenge.antifraudvalidationservice.service.AntiFraudValidationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class TransactionEventListenerTest {

    @InjectMocks
    private TransactionEventListener transactionEventListener;

    @Mock
    private AntiFraudValidationService antiFraudValidationService;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ProcessIncomingEventWhenOperationIsCreate() {
        String eventMsg = "{\"schema\":{\"type\":\"struct\",\"fields\":[{\"type\":\"struct\",\"fields\":[{\"type\":\"string\",\"optional\":false,\"field\":\"transactionexternalid\"},{\"type\":\"string\",\"optional\":false,\"field\":\"accountexternaliddebit\"},{\"type\":\"string\",\"optional\":false,\"field\":\"accountexternalidcredit\"},{\"type\":\"int32\",\"optional\":false,\"field\":\"transfertypeid\"},{\"type\":\"struct\",\"fields\":[{\"type\":\"int32\",\"optional\":false,\"field\":\"scale\"},{\"type\":\"bytes\",\"optional\":false,\"field\":\"value\"}],\"optional\":false,\"name\":\"io.debezium.data.VariableScaleDecimal\",\"version\":1,\"doc\":\"Variable scaled decimal\",\"field\":\"value\"},{\"type\":\"string\",\"optional\":true,\"field\":\"transactiontype\"},{\"type\":\"string\",\"optional\":false,\"field\":\"transactionstatus\"},{\"type\":\"int64\",\"optional\":false,\"name\":\"io.debezium.time.MicroTimestamp\",\"version\":1,\"field\":\"createdat\"}],\"optional\":true,\"name\":\"cqrs-.public.transactions.Value\",\"field\":\"before\"},{\"type\":\"struct\",\"fields\":[{\"type\":\"string\",\"optional\":false,\"field\":\"transactionexternalid\"},{\"type\":\"string\",\"optional\":false,\"field\":\"accountexternaliddebit\"},{\"type\":\"string\",\"optional\":false,\"field\":\"accountexternalidcredit\"},{\"type\":\"int32\",\"optional\":false,\"field\":\"transfertypeid\"},{\"type\":\"struct\",\"fields\":[{\"type\":\"int32\",\"optional\":false,\"field\":\"scale\"},{\"type\":\"bytes\",\"optional\":false,\"field\":\"value\"}],\"optional\":false,\"name\":\"io.debezium.data.VariableScaleDecimal\",\"version\":1,\"doc\":\"Variable scaled decimal\",\"field\":\"value\"},{\"type\":\"string\",\"optional\":true,\"field\":\"transactiontype\"},{\"type\":\"string\",\"optional\":false,\"field\":\"transactionstatus\"},{\"type\":\"int64\",\"optional\":false,\"name\":\"io.debezium.time.MicroTimestamp\",\"version\":1,\"field\":\"createdat\"}],\"optional\":true,\"name\":\"cqrs-.public.transactions.Value\",\"field\":\"after\"},{\"type\":\"struct\",\"fields\":[{\"type\":\"string\",\"optional\":false,\"field\":\"version\"},{\"type\":\"string\",\"optional\":false,\"field\":\"connector\"},{\"type\":\"string\",\"optional\":false,\"field\":\"name\"},{\"type\":\"int64\",\"optional\":false,\"field\":\"ts_ms\"},{\"type\":\"string\",\"optional\":true,\"name\":\"io.debezium.data.Enum\",\"version\":1,\"parameters\":{\"allowed\":\"true,last,false,incremental\"},\"default\":\"false\",\"field\":\"snapshot\"},{\"type\":\"string\",\"optional\":false,\"field\":\"db\"},{\"type\":\"string\",\"optional\":true,\"field\":\"sequence\"},{\"type\":\"string\",\"optional\":false,\"field\":\"schema\"},{\"type\":\"string\",\"optional\":false,\"field\":\"table\"},{\"type\":\"int64\",\"optional\":true,\"field\":\"txId\"},{\"type\":\"int64\",\"optional\":true,\"field\":\"lsn\"},{\"type\":\"int64\",\"optional\":true,\"field\":\"xmin\"}],\"optional\":false,\"name\":\"io.debezium.connector.postgresql.Source\",\"field\":\"source\"},{\"type\":\"string\",\"optional\":false,\"field\":\"op\"},{\"type\":\"int64\",\"optional\":true,\"field\":\"ts_ms\"},{\"type\":\"struct\",\"fields\":[{\"type\":\"string\",\"optional\":false,\"field\":\"id\"},{\"type\":\"int64\",\"optional\":false,\"field\":\"total_order\"},{\"type\":\"int64\",\"optional\":false,\"field\":\"data_collection_order\"}],\"optional\":true,\"name\":\"event.block\",\"version\":1,\"field\":\"transaction\"}],\"optional\":false,\"name\":\"cqrs-.public.transactions.Envelope\",\"version\":1},\"payload\":{\"before\":null,\"after\":{\"transactionexternalid\":\"TX13187f74-0305-4b03-91ba-f7be5a54e8\",\"accountexternaliddebit\":\"PE2212310230111231023012\",\"accountexternalidcredit\":\"PE2212310230111231023011\",\"transfertypeid\":1,\"value\":{\"scale\":0,\"value\":\"aQ==\"},\"transactiontype\":\"TRANSFER\",\"transactionstatus\":\"PENDING\",\"createdat\":1718042912576072},\"source\":{\"version\":\"2.5.0.Final\",\"connector\":\"postgresql\",\"name\":\"cqrs-\",\"ts_ms\":1718060912765,\"snapshot\":\"false\",\"db\":\"postgres\",\"sequence\":\"[\\\"24339856\\\",\\\"24340144\\\"]\",\"schema\":\"public\",\"table\":\"transactions\",\"txId\":774,\"lsn\":24340144,\"xmin\":null},\"op\":\"c\",\"ts_ms\":1718060913008,\"transaction\":null}}";
        transactionEventListener.consumeEvent(eventMsg);

        verify(kafkaTemplate, times(1)).send(anyString(), anyString());
    }

    @Test
    void ProcessIncomingEventWhenOperationIsNotCreate() {
        String eventMsg = "{\"schema\":{\"type\":\"struct\",\"fields\":[{\"type\":\"struct\",\"fields\":[{\"type\":\"string\",\"optional\":false,\"field\":\"transactionexternalid\"},{\"type\":\"string\",\"optional\":false,\"field\":\"accountexternaliddebit\"},{\"type\":\"string\",\"optional\":false,\"field\":\"accountexternalidcredit\"},{\"type\":\"int32\",\"optional\":false,\"field\":\"transfertypeid\"},{\"type\":\"struct\",\"fields\":[{\"type\":\"int32\",\"optional\":false,\"field\":\"scale\"},{\"type\":\"bytes\",\"optional\":false,\"field\":\"value\"}],\"optional\":false,\"name\":\"io.debezium.data.VariableScaleDecimal\",\"version\":1,\"doc\":\"Variable scaled decimal\",\"field\":\"value\"},{\"type\":\"string\",\"optional\":true,\"field\":\"transactiontype\"},{\"type\":\"string\",\"optional\":false,\"field\":\"transactionstatus\"},{\"type\":\"int64\",\"optional\":false,\"name\":\"io.debezium.time.MicroTimestamp\",\"version\":1,\"field\":\"createdat\"}],\"optional\":true,\"name\":\"cqrs-.public.transactions.Value\",\"field\":\"before\"},{\"type\":\"struct\",\"fields\":[{\"type\":\"string\",\"optional\":false,\"field\":\"transactionexternalid\"},{\"type\":\"string\",\"optional\":false,\"field\":\"accountexternaliddebit\"},{\"type\":\"string\",\"optional\":false,\"field\":\"accountexternalidcredit\"},{\"type\":\"int32\",\"optional\":false,\"field\":\"transfertypeid\"},{\"type\":\"struct\",\"fields\":[{\"type\":\"int32\",\"optional\":false,\"field\":\"scale\"},{\"type\":\"bytes\",\"optional\":false,\"field\":\"value\"}],\"optional\":false,\"name\":\"io.debezium.data.VariableScaleDecimal\",\"version\":1,\"doc\":\"Variable scaled decimal\",\"field\":\"value\"},{\"type\":\"string\",\"optional\":true,\"field\":\"transactiontype\"},{\"type\":\"string\",\"optional\":false,\"field\":\"transactionstatus\"},{\"type\":\"int64\",\"optional\":false,\"name\":\"io.debezium.time.MicroTimestamp\",\"version\":1,\"field\":\"createdat\"}],\"optional\":true,\"name\":\"cqrs-.public.transactions.Value\",\"field\":\"after\"},{\"type\":\"struct\",\"fields\":[{\"type\":\"string\",\"optional\":false,\"field\":\"version\"},{\"type\":\"string\",\"optional\":false,\"field\":\"connector\"},{\"type\":\"string\",\"optional\":false,\"field\":\"name\"},{\"type\":\"int64\",\"optional\":false,\"field\":\"ts_ms\"},{\"type\":\"string\",\"optional\":true,\"name\":\"io.debezium.data.Enum\",\"version\":1,\"parameters\":{\"allowed\":\"true,last,false,incremental\"},\"default\":\"false\",\"field\":\"snapshot\"},{\"type\":\"string\",\"optional\":false,\"field\":\"db\"},{\"type\":\"string\",\"optional\":true,\"field\":\"sequence\"},{\"type\":\"string\",\"optional\":false,\"field\":\"schema\"},{\"type\":\"string\",\"optional\":false,\"field\":\"table\"},{\"type\":\"int64\",\"optional\":true,\"field\":\"txId\"},{\"type\":\"int64\",\"optional\":true,\"field\":\"lsn\"},{\"type\":\"int64\",\"optional\":true,\"field\":\"xmin\"}],\"optional\":false,\"name\":\"io.debezium.connector.postgresql.Source\",\"field\":\"source\"},{\"type\":\"string\",\"optional\":false,\"field\":\"op\"},{\"type\":\"int64\",\"optional\":true,\"field\":\"ts_ms\"},{\"type\":\"struct\",\"fields\":[{\"type\":\"string\",\"optional\":false,\"field\":\"id\"},{\"type\":\"int64\",\"optional\":false,\"field\":\"total_order\"},{\"type\":\"int64\",\"optional\":false,\"field\":\"data_collection_order\"}],\"optional\":true,\"name\":\"event.block\",\"version\":1,\"field\":\"transaction\"}],\"optional\":false,\"name\":\"cqrs-.public.transactions.Envelope\",\"version\":1},\"payload\":{\"before\":null,\"after\":{\"transactionexternalid\":\"TX13187f74-0305-4b03-91ba-f7be5a54e8\",\"accountexternaliddebit\":\"PE2212310230111231023012\",\"accountexternalidcredit\":\"PE2212310230111231023011\",\"transfertypeid\":1,\"value\":{\"scale\":0,\"value\":\"aQ==\"},\"transactiontype\":\"TRANSFER\",\"transactionstatus\":\"PENDING\",\"createdat\":1718042912576072},\"source\":{\"version\":\"2.5.0.Final\",\"connector\":\"postgresql\",\"name\":\"cqrs-\",\"ts_ms\":1718060912765,\"snapshot\":\"false\",\"db\":\"postgres\",\"sequence\":\"[\\\"24339856\\\",\\\"24340144\\\"]\",\"schema\":\"public\",\"table\":\"transactions\",\"txId\":774,\"lsn\":24340144,\"xmin\":null},\"op\":\"u\",\"ts_ms\":1718060913008,\"transaction\":null}}";
        transactionEventListener.consumeEvent(eventMsg);

        verify(kafkaTemplate, times(0)).send(anyString(), anyString());
    }

    @Test
    void ThrowExceptionWhenMsgCannotBeConverted() {
        String eventMsg = "invalid message";
        assertThrows(NullPointerException.class, () -> {
            transactionEventListener.consumeEvent(eventMsg);
        });
    }
}