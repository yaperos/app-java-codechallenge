package com.yape.transactional.requestDTO;


import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialTransactionRequestDTO implements Serializable {

    private Long id;
    private String accountExternalIdDebit;
    private String accountExternalIdCredit;
    private Integer tranferTypeId;
    private Double value;

}
