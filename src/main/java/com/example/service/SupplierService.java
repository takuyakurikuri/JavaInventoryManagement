package com.example.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Supplier;
import com.example.repository.SupplierRepository;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }
}
