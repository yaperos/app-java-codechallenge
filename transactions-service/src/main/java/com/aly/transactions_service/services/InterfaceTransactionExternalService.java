package com.aly.transactions_service.services;

import com.aly.transactions_service.model.dtos.Request;
import com.aly.transactions_service.model.entities.TransactionExternal;

public interface InterfaceTransactionExternalService extends INTERFACECRUD<TransactionExternal> {

	TransactionExternal saveTransactionExternal(Request request);

}
