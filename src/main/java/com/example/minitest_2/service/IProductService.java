package com.example.minitest_2.service;

import com.example.minitest_2.conmom.ICRUDService;
import com.example.minitest_2.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductService extends ICRUDService<Product> {
    public List<Product> findAllByNameContaining( String name);

    List<Product> findProductByCategoryContaining(@Param("name") String name);


}
