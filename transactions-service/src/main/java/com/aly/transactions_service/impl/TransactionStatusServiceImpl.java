package com.aly.transactions_service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aly.transactions_service.model.entities.TransactionStatus;
import com.aly.transactions_service.repositories.InterfaceTransactionStatusDao;
import com.aly.transactions_service.services.InterfaceTransactionStatusService;

@Service
public class TransactionStatusServiceImpl implements InterfaceTransactionStatusService {

	@Autowired
	private InterfaceTransactionStatusDao interfaceTransactionStatusDao;

	@Override
	public TransactionStatus save(TransactionStatus t) {
		return interfaceTransactionStatusDao.save(t);
	}

	@Override
	public TransactionStatus update(TransactionStatus t) {
		return interfaceTransactionStatusDao.save(t);
	}

	@Override
	public List<TransactionStatus> list() {
		return interfaceTransactionStatusDao.findAll();
	}

	@Override
	public void delete(Long id) {
		interfaceTransactionStatusDao.deleteById(id);
	}

	@Override
	public Optional<TransactionStatus> listId(Long id) {
		return interfaceTransactionStatusDao.findById(id);
	}
}
