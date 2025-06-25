package com.example.dto;

import com.example.entity.Transaction;

import lombok.Data;

@Data
public class TransactionDTO {
    private Transaction transaction;
    private Long currentStock;


}
