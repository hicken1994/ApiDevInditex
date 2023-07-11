package com.example.ecommerce.repostory;

import com.example.ecommerce.model.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Prices, Long> {
    List<Prices> findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
            LocalDateTime date, LocalDateTime date2, Long productId, Long brandId);
}


