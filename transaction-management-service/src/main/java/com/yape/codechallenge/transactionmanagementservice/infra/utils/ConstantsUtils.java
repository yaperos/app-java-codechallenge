package com.yape.codechallenge.transactionmanagementservice.infra.utils;

public class ConstantsUtils {
    private ConstantsUtils() {
    }
    public static final String TRANSACTIONS_NAME = "transactions";

    public static class TransactionMapper {
        public static final String TRANSACTION_EXTERNAL_ID = "transactionexternalid";
        public static final String CREATED_AT = "createdat";
        public static final String TRANSACTION_STATUS = "transactionstatus";
        public static final String TRANSACTION_TYPE = "transactiontype";
        public static final String VALUE = "value";
        private TransactionMapper() {
        }
    }
    public static class EventConstants {
        public static final String CONSUMER_AF_TOPIC = "transaction-status";
        public static final String CONSUMER_DB_TOPIC = "cqrs-.public.*";
        public static final String GROUP_ID_1 = "group1";
        public static final String GROUP_ID_3 = "group3";

        private EventConstants() {
        }
    }
}
