package com.aly.transactions_service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.aly.transactions_service.events.OrderEvent;
import com.aly.transactions_service.repositories.InterfaceTransactionStatusDao;
import com.aly.transactions_service.repositories.InterfaceTransactionTypeDao;
import com.aly.transactions_service.services.InterfaceTransactionExternalService;
import com.aly.transactions_service.util.JsonTransferUtil;
import com.aly.transactions_service.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.aly.transactions_service.model.dtos.Request;
import com.aly.transactions_service.model.entities.TransactionExternal;
import com.aly.transactions_service.model.entities.TransactionStatus;
import com.aly.transactions_service.model.entities.TransactionType;
import com.aly.transactions_service.repositories.InterfaceTransactionExternalDao;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class TransactionExternaServiceImpl implements InterfaceTransactionExternalService {

	@Autowired
	private InterfaceTransactionExternalDao interfaceTransactionExternalDao;
	@Autowired
	private InterfaceTransactionTypeDao interfaceTransactionTypeDao;
	@Autowired
	private InterfaceTransactionStatusDao interfaceTransactionStatusDao;
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	//@Autowired
	private  final WebClient.Builder webClientBuilder;
    public TransactionExternaServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

	@Override
	public TransactionExternal save(TransactionExternal t) {
		return interfaceTransactionExternalDao.save(t);
	}

	@Override
	public TransactionExternal update(TransactionExternal t) {
		return interfaceTransactionExternalDao.save(t);
	}

	@Override
	public List<TransactionExternal> list() {
		return interfaceTransactionExternalDao.findAll();
	}

	@Override
	public void delete(Long id) {
		interfaceTransactionExternalDao.deleteById(id);
	}

	@Override
	public Optional<TransactionExternal> listId(Long id) {
		return interfaceTransactionExternalDao.findById(id);
	}

	@Override
	public TransactionExternal saveTransactionExternal(Request request) {
		log.info("request {} : " ,  JsonTransferUtil.objectToJson(request));
		TransactionExternal transactionExternal = new TransactionExternal();
		TransactionType transactionType = new TransactionType();
		Date today = new Date();
		Long idTransactionType  = interfaceTransactionTypeDao.listarTransactionTypeId("1");
		Long idTransactionStatus  = interfaceTransactionStatusDao.listarTransactionStatusId("1");
		log.info("idTransactionType {} : " ,  idTransactionType);
		log.info("idTransactionStatus {} : " ,  idTransactionStatus);
		transactionType.setId(idTransactionType);
		TransactionStatus transactionStatus = new TransactionStatus();
		transactionStatus.setId(idTransactionStatus);

		transactionExternal.setId((long) 0);
		transactionExternal.setTransactionType(transactionType);
		transactionExternal.setTransactionStatus(transactionStatus);
		transactionExternal.setValue(request.getValue());
		transactionExternal.setUserCreation("testUsuario");
		transactionExternal.setDateCreation(today);

		transactionExternal = interfaceTransactionExternalDao.save(transactionExternal);

		TransactionExternal result = this.webClientBuilder.build()
				.post()
				.uri("lb://antifraudes-service/api/antifraudes/consultAntiFrau")
				//.uri("http://localhost:8083/api/antifraudes/consultAntiFrau")
				.bodyValue(transactionExternal)
				.retrieve()
				.bodyToMono(TransactionExternal.class)
				.block();
		log.info("result {} : " ,  JsonTransferUtil.objectToJson(result));

		if (result != null) {
			TransactionStatus transactionStatusTest = new TransactionStatus();
			transactionStatusTest.setId(interfaceTransactionStatusDao.listarTransactionStatusId(result.getTransactionStatus().getCode()));
			result.setTransactionStatus(transactionStatusTest);
			result.setUserUpdate("testUsuario");
			result.setDateUpdate(today);
		} else {
			throw new IllegalArgumentException("not information");
		}
		var savedOrder = interfaceTransactionExternalDao.save(result);
		//Send message to order topic
		this.kafkaTemplate.send("orders-topic", JsonUtils.toJson(
				new OrderEvent(savedOrder.getTransactionStatus().getName())
		));
		log.info("response kafka {} : " ,  JsonTransferUtil.objectToJson(savedOrder));
		return savedOrder;
	}
}
