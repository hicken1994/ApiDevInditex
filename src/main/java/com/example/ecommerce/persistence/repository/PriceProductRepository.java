package com.example.ecommerce.persistence.repository;

import com.example.ecommerce.domain.entities.PriceProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

    public interface PriceProductRepository extends JpaRepository<PriceProduct, Long>, CustomPriceProductRepository {

}



