package com.yape.codechallenge.transactionmanagementservice.infra.outputport;

public interface CommandRepository {
    public <T> T save( T reg );

    public <T> T getById( String id, Class<T> clazz );
}
