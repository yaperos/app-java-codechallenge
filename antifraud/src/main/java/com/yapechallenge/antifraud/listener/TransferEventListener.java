package com.yapechallenge.antifraud.listener;

import com.yapechallenge.antifraud.events.TransferEvent;
import com.yapechallenge.antifraud.model.enums.TransferStatus;
import com.yapechallenge.antifraud.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Component
public class TransferEventListener {

    Logger logger = LoggerFactory.getLogger(TransferEventListener.class);
    @Value("${app.antifraud.max-amount}")
    private int MAX_AMOUNT;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @KafkaListener(topics="transfer-topic")
    public void handleTransfersNotification(String message){

        TransferEvent transferEventRpt;

        var transferEvent= JsonUtils.fromJSON(message, TransferEvent.class);

        try {
            if(transferEvent.amount()>MAX_AMOUNT)
                transferEventRpt = new TransferEvent(transferEvent.transferId(),transferEvent.amount(), TransferStatus.REJECTED);
            else
                transferEventRpt = new TransferEvent(transferEvent.transferId(),transferEvent.amount(), TransferStatus.APPROVED);

            kafkaTemplate.send("transfer-rpta-topic", JsonUtils.toJSON(transferEventRpt));

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
