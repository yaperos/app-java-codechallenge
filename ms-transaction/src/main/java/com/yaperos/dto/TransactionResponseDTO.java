package com.yaperos.dto;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TransactionResponseDTO {
    private UUID transactionExternalId;
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;
    private double value;
    private Date createdAt;

    @Data
    public static class TransactionType {
        private String name;
    }

    @Data
    public static class TransactionStatus {
        private String name;
    }
}
