package com.example.springboot.repositories;

import com.example.springboot.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IProductiveRepository extends JpaRepository<ProductModel, UUID> {
}
