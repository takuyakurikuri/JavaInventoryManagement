package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.dto.ExpirationCheckDto;
import com.example.entity.Item;
import com.example.entity.Store;
import com.example.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("""
        SELECT new com.example.dto.ExpirationCheckDto(
            t.id, i.janCode, i.name, c.name, s.name, t.expirationDate
        )
        FROM Transaction t
        JOIN t.item i
        JOIN i.category c
        JOIN t.store s
        WHERE t.type = 'IN'
          AND t.expirationDate IS NOT NULL
          AND t.checked = false
        ORDER BY t.expirationDate ASC
    """)
    List<ExpirationCheckDto> findAllUnconfirmedExpirations();

    @Query("""
        SELECT new com.example.dto.ExpirationCheckDto(
            t.id, i.janCode, i.name, c.name, '', t.expirationDate
        )
        FROM Transaction t
        JOIN t.item i
        JOIN i.category c
        WHERE t.type = 'IN'
          AND t.expirationDate IS NOT NULL
          AND t.checked = false
          AND t.store.id = :storeId
        ORDER BY t.expirationDate ASC
    """)
    List<ExpirationCheckDto> findUnconfirmedExpirationsByStore(@Param("storeId") Long storeId);

    List<Transaction> findByStoreAndItem(Store store, Item item);
    List<Transaction> findByStoreOrderByCreatedAtDesc(Store store);

}
