package com.example.minitest_2.service.impl;

import com.example.minitest_2.model.Product;
import com.example.minitest_2.repository.ProductRepository;
import com.example.minitest_2.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAllByNameContaining( String name) {
        return productRepository.findAllByNameContaining(name);
    }

    @Override
    public List<Product> findProductByCategoryContaining(String name) {
        return productRepository.findProductByCategoryContaining(name);
    }

}

