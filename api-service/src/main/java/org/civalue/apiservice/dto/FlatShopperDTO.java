package org.civalue.apiservice.dto;

import lombok.Data;

@Data
public class FlatShopperDTO {

    private String shopperId;
    private String productId;
    private Double relevancyScore;
}
