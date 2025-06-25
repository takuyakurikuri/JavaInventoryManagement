package com.example.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "JANコードを入力してください")
    private Long janCode;

    @Column(nullable = false)
    @NotBlank(message = "名称を入力してください")
    private String name;

    @JoinColumn(name = "category_id",nullable = false)
    @ManyToOne
    private Category category;

    @Column(nullable = false)
    @NotNull(message = "税抜金額を入力してください")
    private Long price;

    @Column(nullable = false)
    @NotNull(message = "標準在庫数を入力してください")
    private Long stockStd;

    @Column(nullable = false)
    @NotNull(message = "発注単価を入力してください")
    private Long orderUnit;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

}
