package com.example.ecommerce.domain.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@Builder
@Table(name="PRICES")
public class PriceProduct {


   @Column(name="productId")
   private int productId;

   @Column(name="brandId")
   private int brandId;

   @Column(name="priceList")
   private int priceList;

   @Column(name="priority")
   private int priority;

   @Column(name="Start_Date")
   private LocalDateTime startDate;

   @Column(name="End_Date")
   private LocalDateTime endDate;

   @Id
   @Column(name="price")
   private BigDecimal price;

   @Column(name="curr")
   private String curr;

   @Column(name="Last_Update")
   private LocalDateTime lastUpdateDate;

   @Column(name="Last_Update_By")
   private String lastUpdateBy;


   public PriceProduct(int productId, int brandId, int priceList, int priority, LocalDateTime startDate, LocalDateTime endDate, BigDecimal price, String curr, LocalDateTime lastUpdateDate, String lastUpdateBy) {
      this.productId = productId;
      this.brandId = brandId;
      this.priceList = priceList;
      this.priority = priority;
      this.startDate = startDate;
      this.endDate = endDate;
      this.price = price;
      this.curr = curr;
      this.lastUpdateDate = lastUpdateDate;
      this.lastUpdateBy = lastUpdateBy;
   }

   public PriceProduct() {

   }




}