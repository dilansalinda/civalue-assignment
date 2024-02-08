package org.civalue.apiservice.schdular;


import org.civalue.apiservice.config.AppConfig;
import org.civalue.apiservice.dto.FlatShopperDTO;
import org.civalue.apiservice.dto.ShopperDTO;
import org.civalue.apiservice.util.ShopperDTOMapper;
import org.civalue.apiservice.model.Product;
import org.civalue.apiservice.service.ProductService;
import org.civalue.apiservice.service.ShopperService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Objects;


@Component
public class DataTeamScheduler {


    private RestClient restClient;

    private final AppConfig appConfig;

    private final ProductService productService;
    private final ShopperDTOMapper shopperDTOMapper;

    private final ShopperService shopperService;

    public DataTeamScheduler(RestClient.Builder builder, AppConfig appConfig, ProductService productService, ShopperDTOMapper shopperDTOMapper, ShopperService shopperService) {
        this.shopperDTOMapper = shopperDTOMapper;
        this.restClient = builder.baseUrl(appConfig.getDataTeamServiceBaseURL()).build();
        this.appConfig = appConfig;
        this.productService = productService;
        this.shopperService = shopperService;
    }

    /**
     * Consider Shopper shelf data Receiving from data team service via RestAPI endpoint
     * http://localhost:8080/api/v1/shoppers
     * (Which is already implemented using Apache Spark in ciValue solution)
     *
     * In here, every 3 seconds (in default) fetching Shopper shelf data from data team service
     */
    @Scheduled(fixedDelayString = "${shopper.data.receiving.in:3000}")
    public void fetchShopperData() {
        List<ShopperDTO> shopperList =  restClient.get()
                .uri("/shoppers")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        if(Objects.nonNull(shopperList)) {
            List<FlatShopperDTO> flatShopperDTOS = shopperDTOMapper.mapToFlatShopperDTO(shopperList);
            shopperService.saveShoppers(flatShopperDTOS);
        }


    }

    /**
     * Consider Project Metadata Receiving from data team service via RestAPI endpoint
     * http://localhost:8080/api/v1/products
     * Which is already implemented using Apache Spark in ciValue solution
     *
     * In here, every 4 seconds (in default) fetching Project Metadata from data team service
     */
    @Scheduled(fixedDelayString = "${product.data.receiving.in:4000}")
    public void  fetchProductMetaData() {
        List<Product> productList = restClient.get()
                .uri("/products")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Product>>() {
                });

        productService.save(productList);
    }


}
