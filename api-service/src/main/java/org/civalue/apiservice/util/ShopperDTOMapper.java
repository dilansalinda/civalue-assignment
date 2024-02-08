package org.civalue.apiservice.util;

import org.civalue.apiservice.dto.FlatShopperDTO;
import org.civalue.apiservice.dto.ShelfItemDTO;
import org.civalue.apiservice.dto.ShopperDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShopperDTOMapper {


 /**
 *  mapToFlatShopperDTO for flatting out the nested ShopperDTO List of objects
 * */
    public List<FlatShopperDTO> mapToFlatShopperDTO(List<ShopperDTO> shoppers) {
        List<FlatShopperDTO> flatShoppers = new ArrayList<>();

        for (ShopperDTO shopper : shoppers) {
            String shopperId = shopper.getShopperId();
            List<ShelfItemDTO> shelfItems = shopper.getShelf();

            for (ShelfItemDTO shelfItem : shelfItems) {
                FlatShopperDTO flatShopper = new FlatShopperDTO();
                flatShopper.setShopperId(shopperId);
                flatShopper.setProductId(shelfItem.getProductId());
                flatShopper.setRelevancyScore(shelfItem.getRelevancyScore());
                flatShoppers.add(flatShopper);
            }
        }

        return flatShoppers;
    }
}
