package com.example.ecommerce.service;

import com.example.ecommerce.Dto.PriceDto;
import com.example.ecommerce.model.Prices;
import com.example.ecommerce.repostory.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceService {
    private final PriceRepository priceRepository;

    @Autowired
    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public PriceDto getPriceByParameters(LocalDateTime date, Long productId, Long brandId) {
        List<Prices> prices = priceRepository
                .findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
                        date, date, productId, brandId);

        if (prices.isEmpty()) {
            return null;
        }

        Prices priceToApply = prices.get(0);

        PriceDto priceDto = new PriceDto();
        priceDto.setProductId(priceToApply.getProductId());
        priceDto.setBrandId(priceToApply.getBrand().getId());
        priceDto.setPriceList(priceToApply.getPriceList());
        priceDto.setStartDate(priceToApply.getStartDate());
        priceDto.setEndDate(priceToApply.getEndDate());
        priceDto.setPrice(priceToApply.getPrice());
        priceDto.setCurrency(priceToApply.getCurrency());

        return priceDto;
    }
}