package org.civalue.apiservice.service;

import org.civalue.apiservice.model.Product;
import org.civalue.apiservice.model.Shopper;
import org.civalue.apiservice.repository.ProductRepository;
import org.civalue.apiservice.repository.ShopperRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ShopperRepository shopperRepository;

    public ProductService(ProductRepository productRepository, ShopperRepository shopperRepository) {
        this.productRepository = productRepository;
        this.shopperRepository = shopperRepository;
    }

    @Cacheable("allProducts")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //todo : check before product is exist
    public void save(List<Product> products) {
        productRepository.saveAll(products);
    }


    /**
     * Filter products based on the filter parameters (shopperId, category and brand)
     */

    public List<Product> getProductsByShopper(String shopperId, String category, String brand, int limit) {

        List<Shopper> shoppers = shopperRepository.findByShopperId(shopperId);
        List<Product> products = getAllProducts();

        products.removeIf(product -> shoppers.stream()
                .noneMatch(shopper -> shopper.getProductId()
                        .equals(product.getProductId())));

        if (category != null) {
            products.removeIf(product -> !product.getCategory().equals(category));
        }
        if (brand != null) {
            products.removeIf(product -> !product.getBrand().equals(brand));
        }

        if (limit <= 0 || limit > 100) {
            limit = 100;
        }

        if (products.size() > limit) {
            return products.subList(0, limit);
        } else {
            return products;
        }

    }

    /**
     * Filter the shoppers by productId
     * */
    public List<Shopper> getShopperByProduct(String productId, int limit) {

        List<Shopper> shoppers = shopperRepository.findByProductId(productId);

        if (limit <= 0 || limit > 1000) {
            limit = 1000;
        }
        if (shoppers.size() > limit) {
            return shoppers.subList(0, limit);
        } else {
            return shoppers;
        }
    }

}
