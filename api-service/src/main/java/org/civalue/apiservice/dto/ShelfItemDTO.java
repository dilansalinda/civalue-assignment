package org.civalue.apiservice.dto;

import lombok.Data;

@Data
public class ShelfItemDTO {

    private String productId;
    private Double relevancyScore;
}
