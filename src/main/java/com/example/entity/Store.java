package com.example.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "店舗名を入力してください")
    @Size(max = 100, message = "店舗名は100文字以内で入力してください")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "所在地を入力してください")
    @Size(max = 255, message = "所在地は255文字以内で入力してください")
    private String location;

    @Column(nullable = false)
    private LocalDateTime createdAt;



}
