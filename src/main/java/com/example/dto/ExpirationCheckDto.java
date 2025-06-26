package com.example.dto;


import java.time.LocalDate;

import lombok.Data;

@Data
public class ExpirationCheckDto {
    private Long transactionId;
    private String janCode;
    private String itemName;
    private String categoryName;
    private String storeName;
    private LocalDate expirationDate;
    private boolean isNearExpiration;

    public ExpirationCheckDto(Long transactionId, String janCode, String itemName, String categoryName, String storeName, LocalDate expirationDate) {
        this.transactionId = transactionId;
        this.janCode = janCode;
        this.itemName = itemName;
        this.categoryName = categoryName;
        this.storeName = storeName;
        this.expirationDate = expirationDate;
        this.isNearExpiration = expirationDate != null && !expirationDate.isBefore(LocalDate.now()) && !expirationDate.isAfter(LocalDate.now().plusDays(14));
    }
}
