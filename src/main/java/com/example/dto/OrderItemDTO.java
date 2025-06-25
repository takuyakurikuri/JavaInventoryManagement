package com.example.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long itemId;
    private String itemName;
    private String categoryName;
    private Long orderUnit;
    private Long stockStd;
    private Integer currentQuantity;  // inventory.quantity
    private Integer orderQuantity;    // フォーム編集対象
}