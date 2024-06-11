package com.aly.transactions_service.impl;

import java.util.List;
import java.util.Optional;

import com.aly.transactions_service.model.entities.TransactionType;
import com.aly.transactions_service.repositories.InterfaceTransactionTypeDao;
import com.aly.transactions_service.services.InterfaceTransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionTypeServiceImpl implements InterfaceTransactionTypeService {

	@Autowired
	private InterfaceTransactionTypeDao interfaceTransactionTypeDao;

	@Override
	public TransactionType save(TransactionType t) {
		return interfaceTransactionTypeDao.save(t);
	}

	@Override
	public TransactionType update(TransactionType t) {
		return interfaceTransactionTypeDao.save(t);
	}

	@Override
	public List<TransactionType> list() {
		return interfaceTransactionTypeDao.findAll();
	}

	@Override
	public void delete(Long id) {
		interfaceTransactionTypeDao.deleteById(id);
	}

	@Override
	public Optional<TransactionType> listId(Long id) {
		return interfaceTransactionTypeDao.findById(id);
	}
}
