package com.yape.challenge.service;

import com.yape.challenge.dao.TransferRepository;
import com.yape.challenge.events.TransferEvent;
import com.yape.challenge.model.dtos.StatusDto;
import com.yape.challenge.model.dtos.TransferDto;
import com.yape.challenge.model.dtos.TransferRptDto;
import com.yape.challenge.model.dtos.TypeDto;
import com.yape.challenge.model.entities.TransactionStatus;
import com.yape.challenge.model.entities.TransactionType;
import com.yape.challenge.model.entities.Transfer;
import com.yape.challenge.model.enums.TransferStatus;
import com.yape.challenge.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    Logger logger = LoggerFactory.getLogger(TransferService.class);

    public List<TransferRptDto> getTransfers(){
        List<Transfer> lsTransfer;
        List<TransferRptDto> lsTransferDto=null;

        try{
            lsTransfer = (List<Transfer>) transferRepository.findAll();
            lsTransferDto = lsTransfer.stream().map(x->{
                return   new TransferRptDto(x.getAccountDebit(),x.getAccountCredit(),new TypeDto(x.getTransactionType().getName()),new StatusDto(x.getTransactionStatus().getName()),x.getAmount(),x.getCreatedAt());
            }).toList();
        }catch (Exception e){
            logger.error(e.getMessage());
        }

        return lsTransferDto;
    }

    public TransferDto saveTransfer(TransferDto transfer){

        Transfer newTransfer = new Transfer();

        try {
            newTransfer.setAccountDebit(transfer.getAccountExternalIdDebit());
            newTransfer.setAccountCredit(transfer.getAccountExternalIdCredit());
            newTransfer.setAmount(transfer.getValue());
            newTransfer.setTransactionType(new TransactionType(Long.parseLong(transfer.getTranferTypeId())));
            newTransfer.setTransactionStatus(new TransactionStatus(TransferStatus.PENDING.getTypeId()));

            newTransfer = transferRepository.save(newTransfer);

            transfer.setCreatedAt(newTransfer.getCreatedAt());

            kafkaTemplate.send("transfer-topic", JsonUtils.toJSON(
                    new TransferEvent(newTransfer.getId(),newTransfer.getAmount(), TransferStatus.PENDING)
            ));

            logger.info(
                    "Se guardó la transacción por el monto de S/ " + newTransfer.getAmount() + " con el estado " + TransferStatus.PENDING + " (id: " + newTransfer.getId() + ")"
            );
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return transfer;
    }

    public void updateTransfer(Transfer transfer){

        Transfer tmpTransfer;

        try {
            tmpTransfer = transferRepository.findById(transfer.getId()).orElse(null);

            tmpTransfer.setTransactionStatus(new TransactionStatus(transfer.getTransactionStatus().getId()));

            logger.info(
                    "Se ejecutó el evento de Antifraude para la transacción " + transfer.getId()
            );

            transferRepository.save(tmpTransfer);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
