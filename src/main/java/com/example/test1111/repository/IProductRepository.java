package com.example.test1111.repository;

import com.example.test1111.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Products, Long> {

    List<Products> findAllByNameContaining(String name);
}
