package com.example.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderRequestForm {
    private List<OrderItemInput> details;

    @Data
    public static class OrderItemInput {
        private Long itemId;
        private Integer quantity;
    }
}
