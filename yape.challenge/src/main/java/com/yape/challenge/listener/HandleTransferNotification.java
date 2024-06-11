package com.yape.challenge.listener;

import com.yape.challenge.events.TransferEvent;
import com.yape.challenge.model.entities.TransactionStatus;
import com.yape.challenge.model.entities.Transfer;
import com.yape.challenge.model.enums.TransferStatus;
import com.yape.challenge.service.TransferService;
import com.yape.challenge.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class HandleTransferNotification {

    @Autowired
    TransferService transferService;

    @KafkaListener(topics="transfer-rpta-topic")
    public void handleTransfersNotification(String message){

        var transferEvent= JsonUtils.fromJSON(message, TransferEvent.class);

        Transfer updateTransfer= new Transfer();
        updateTransfer.setId(transferEvent.transferId());
        updateTransfer.setTransactionStatus(new TransactionStatus(TransferStatus.fromName(transferEvent.transferStatus().toString()).getTypeId()));

        transferService.updateTransfer(updateTransfer);

    }


}
