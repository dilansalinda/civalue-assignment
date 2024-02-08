package org.civalue.apiservice.controller;

import org.civalue.apiservice.model.Product;
import org.civalue.apiservice.model.Shopper;
import org.civalue.apiservice.service.ProductService;
import org.civalue.apiservice.service.ShopperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/external")
public class ExternalController {

    private final ProductService productService;

    public ExternalController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProductsData(@RequestParam(value = "shopperId") String shopperId,
                                                         @RequestParam(value = "category", required = false) String category,
                                                         @RequestParam(value = "brand", required = false) String brand,
                                                         @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

       List<Product> productList = productService.getProductsByShopper(shopperId,category,brand,limit);
        if (Objects.nonNull(productList)) {
            return ResponseEntity.ok(productList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/shopper")
    public ResponseEntity<List<Shopper>> getShopperData(@RequestParam(value = "productId") String productId,
                                                        @RequestParam(value = "limit",required = false, defaultValue = "10") int limit) {


        List<Shopper> shopperList = productService.getShopperByProduct(productId, limit);
        if (Objects.nonNull(shopperList)) {
            return ResponseEntity.ok(shopperList);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}