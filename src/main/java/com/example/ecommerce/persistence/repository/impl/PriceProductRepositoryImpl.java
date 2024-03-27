package com.example.ecommerce.persistence.repository.impl;

import com.example.ecommerce.domain.entities.PriceProduct;
import com.example.ecommerce.persistence.repository.CustomPriceProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PriceProductRepositoryImpl implements CustomPriceProductRepository {
    @PersistenceContext
    private EntityManager entityManager;
    private final String TABLE_NAME = "PRICES";

    private final String QUERY_GET_PRICE_PRODUCT = String.format(
            "SELECT Brand_Id, Product_Id, Price_List, Start_Date, End_Date, Priority, Price, Curr, Last_Update, Last_Update_By " +
                    "FROM %s WHERE Brand_id = ?1 AND Product_Id = ?2 AND Start_Date <= ?3 AND End_Date >= ?4 ORDER BY priority DESC LIMIT 1",
            TABLE_NAME);

    public PriceProduct get(int brandId, int productId, LocalDateTime date) {
        try {
            return getProduct(brandId, productId, date);
        } catch (Exception e) {
            log.error("Error when retrieving data for brandId: {}, productId: {}, and Start_Date: {}", brandId, productId, date, e);
            return null;
        }
    }

    public  PriceProduct getProduct(int brandId, int productId, LocalDateTime date) {
        var pricesList = entityManager
                .createNativeQuery(QUERY_GET_PRICE_PRODUCT, PriceProduct.class)
                .setParameter(1, brandId)
                .setParameter(2, productId)
                .setParameter(3, date)
                .setParameter(4, date)
                .getResultList();
        if (pricesList.isEmpty()){
            return null;
        } else {
            return (PriceProduct) pricesList.get(0);
        }
    }

}