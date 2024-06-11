package com.aly.transactions_service.services;

import java.util.List;
import java.util.Optional;

public interface INTERFACECRUD<T> {

	T save(T t);

	T update(T t);

	List<T> list();

	void delete(Long id);

	Optional<T> listId(Long id);
}
