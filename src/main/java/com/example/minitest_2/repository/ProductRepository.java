package com.example.minitest_2.repository;

import com.example.minitest_2.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findAllByNameContaining( String name);

//    @Query(value = "select * from product join category on category.id = product.category_id where category.name like :name", nativeQuery = true)
//    Page<Product> findProductByCategoryContaining(@Param("name") String name, Pageable pageable);

    @Query(value = "select * from product join category on category.id = product.category_id where category.id = category_id", nativeQuery = true)
    List<Product> findProductByCategoryContaining(@Param("name") String name);



}
