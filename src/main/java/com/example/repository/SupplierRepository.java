package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
