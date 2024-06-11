package com.yape.codechallenge.transactionmanagementservice.infra.inputport;

import com.yape.codechallenge.transactionmanagementservice.domain.Transactions;

import java.util.Map;

public interface MessageBrokerInputPort {
        void updateReg( String table, Map<String, Object> reg );
        void insertReg( String table, Map<String, Object> reg );
        Transactions getById(String table, String id );
}
