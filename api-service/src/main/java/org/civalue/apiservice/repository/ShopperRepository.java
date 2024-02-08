package org.civalue.apiservice.repository;

import org.civalue.apiservice.model.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper,Long> {

        Shopper findByShopperIdAndProductId(String shopperId, String productId);

        List<Shopper> findByShopperId(String shopperId);

        List<Shopper> findByProductId(String productId);
}
