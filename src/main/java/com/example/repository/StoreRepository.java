package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

}
