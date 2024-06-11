package com.aly.antifraudes_service.services;

import com.aly.antifraudes_service.model.dtos.TransactionExternalRequest;

public interface InterfaceAntiFrauConsultService {

	TransactionExternalRequest consultAntiFrau(TransactionExternalRequest transactionExternalRequest);

}
