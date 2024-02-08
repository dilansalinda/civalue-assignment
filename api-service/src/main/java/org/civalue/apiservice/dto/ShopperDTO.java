package org.civalue.apiservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShopperDTO {

    private String shopperId;
    private List<ShelfItemDTO> shelf;
}
