package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.OrderRequest;

public interface OrderRequestRepository extends JpaRepository<OrderRequest, Long> {
    List<OrderRequest> findAllByOrderByRequestDateDesc();

}
