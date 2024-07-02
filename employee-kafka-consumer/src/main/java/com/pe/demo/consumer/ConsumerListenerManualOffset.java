package com.pe.demo.consumer;
 
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.w3c.dom.Entity;

import com.pe.demo.model.entity.TransactionEntity;
import com.pe.demo.repository.TransactionRepository;
import com.pe.demo.util.GsonConverter;

/**
 * Class: ConsumerListenerManualOffset.
 * @author Relari
 */

@Slf4j
@Component
public class ConsumerListenerManualOffset implements AcknowledgingMessageListener<Integer,String>{
	
	@Autowired

	private TransactionRepository transactionRepository;
	 

	@Override
	@KafkaListener(topics = {"transactionEventTopic"})
	public void onMessage(ConsumerRecord<Integer, String> consumerRecord, Acknowledgment acknowledgment) {
		log.info("*************** MICROSERVICE TRANSACTION CONSUMER *******************");

		TransactionEntity entity = GsonConverter.readDataFromJson(consumerRecord.value(), TransactionEntity.class);
		log.debug(entity.toString());
		
		 // Actualizar estado de la transacción en la base de datos
		if (entity.getValue() > 1000) {
			entity.setTransactionStatus("Rechazado");
		 } else  {
		    entity.setTransactionStatus("Aprobado");
	   } 
	     
		 //Se guarda la transacion con el estado seteado lineas arriba.
		transactionRepository.save(entity);

		acknowledgment.acknowledge();
	}

}
