package org.civalue.apiservice.service;

import lombok.extern.slf4j.Slf4j;
import org.civalue.apiservice.dto.FlatShopperDTO;
import org.civalue.apiservice.dto.ShopperDTO;
import org.civalue.apiservice.model.Product;
import org.civalue.apiservice.model.Shopper;
import org.civalue.apiservice.repository.ShopperRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ShopperService {

    private RestClient restClient;

    @Value("${data.team.service.base.url}")
    String dataTeamServiceBaseURL;

    private ShopperRepository shopperRepository;

    public ShopperService(ShopperRepository shopperRepository, RestClient.Builder builder) {
        this.shopperRepository = shopperRepository;
        this.restClient = builder.baseUrl("http://localhost:8080/api/v1").build();
    }



    public void saveShoppers(List<FlatShopperDTO> shopperDTOList) {
        shopperDTOList.stream().forEach(shopperObj -> {
            Shopper shopper = checkShopperSelfItemExists(shopperObj.getShopperId(), shopperObj.getProductId());
            if (Objects.isNull(shopper)) {
                shopper = new Shopper();
                BeanUtils.copyProperties(shopperObj, shopper);
            } else {
                log.debug("Shopper ({}) shelf Item ({}) already exists!!", shopper.getShopperId(), shopper.getProductId());
            }
            shopperRepository.save(shopper);
        });

    }


    private Shopper checkShopperSelfItemExists(String shopperId, String productId) {
        return shopperRepository.findByShopperIdAndProductId(shopperId, productId);
    }






}
