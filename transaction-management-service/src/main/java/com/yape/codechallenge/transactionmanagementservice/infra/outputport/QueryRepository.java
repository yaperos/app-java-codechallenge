package com.yape.codechallenge.transactionmanagementservice.infra.outputport;

import java.util.Map;

public interface QueryRepository {

    void save(Map<String,Object> reg, Class<?> clazz );

    Map<String,Object> getById( String id, Class<?> clazz );
}
