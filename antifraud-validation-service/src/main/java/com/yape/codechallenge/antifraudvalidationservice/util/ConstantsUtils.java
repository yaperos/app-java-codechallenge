package com.yape.codechallenge.antifraudvalidationservice.util;

public class ConstantsUtils {
    private ConstantsUtils() {
    }
    public static final String MAP_TRANSACTION_EXTERNAL_ID = "transactionexternalid";
    public static final String MAP_TRANSACTION_STATUS = "transactionstatus";
    public static final String MAP_VALUE = "value";
    public static final String MAP_SCALE = "scale";
    public static final String MAP_OP = "op";
    public static final String MAP_SOURCE = "source";
    public static final String MAP_TABLE = "table";
    public static final String MAP_PAYLOAD = "payload";
    public static final String MAP_AFTER = "after";

    public static final String KAFKA_BOOSTRAP_SERVERS = "127.0.0.1:29092";
    public static final String PRODUCER_TOPIC = "transaction-status";
    public static final String CONSUMER_TOPIC = "cqrs-.public.*";
    public static final String GROUP_ID = "group2";

    public static final String TRANSACTION_STATUS_ACCEPTED = "ACCEPTED";
    public static final String TRANSACTION_STATUS_REJECTED = "REJECTED";
    public static final String TRANSACTION_DB_OPERATION_CREATE = "c";
}
