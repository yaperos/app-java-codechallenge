package com.aly.antifraudes_service.impl;

import com.aly.antifraudes_service.model.dtos.TransactionExternalRequest;
import com.aly.antifraudes_service.model.dtos.TransactionStatusRequest;
import com.aly.antifraudes_service.services.InterfaceAntiFrauConsultService;
import com.aly.antifraudes_service.util.JsonTransferUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class AntiFrauConsultServiceImpl implements InterfaceAntiFrauConsultService {

	@Override
	public TransactionExternalRequest consultAntiFrau(TransactionExternalRequest transactionExternalRequest) {
		log.info("transactionExternal 1 {} : " ,  JsonTransferUtil.objectToJson(transactionExternalRequest));
		TransactionStatusRequest transactionStatusRequest = new TransactionStatusRequest();
		if (transactionExternalRequest.getValue() > 1000) {
			transactionStatusRequest.setCode("3");
			transactionExternalRequest.setTransactionStatus(transactionStatusRequest);
		} else {
			transactionStatusRequest.setCode("2");
			transactionExternalRequest.setTransactionStatus(transactionStatusRequest);
		}
		log.info("transactionExternal 2 {} : " ,  JsonTransferUtil.objectToJson(transactionExternalRequest));
		return transactionExternalRequest;
	}
}
