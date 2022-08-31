package com.example.minitest_2.controller;

import com.example.minitest_2.model.Category;
import com.example.minitest_2.model.Product;
import com.example.minitest_2.service.ICategoryService;
import com.example.minitest_2.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> productPage = iProductService.findAll();
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Long id) {
        Optional<Product> optionalProduct = iProductService.findById(id);
        if (optionalProduct.isPresent()) {
            return new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product productCreate = iProductService.save(product);
        return new ResponseEntity<>(productCreate, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id,
                                                 @RequestBody Product product) {
        Optional<Product> optionalProduct = iProductService.findById(id);
        if (optionalProduct.isPresent()) {
            product.setId(id);
            Product productUpdate = iProductService.save(product);
            return new ResponseEntity<>(productUpdate, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Product> deleteById(@PathVariable("id") Long id) {
        Optional<Product> optionalProduct = iProductService.findById(id);
        if (optionalProduct.isPresent()) {
            iProductService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/category/create")
    private ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category categoryCreate = iCategoryService.save(category);
        return new ResponseEntity<>(categoryCreate, HttpStatus.CREATED);
    }

    @PutMapping("/category/update/{id}")
    private ResponseEntity<Category> updateCategory(@RequestBody Category category,
                                                    @PathVariable("id") Long id) {
        Optional<Category> optionalCategory = iCategoryService.findById(id);
        if (optionalCategory.isPresent()) {
            category.setId(id);
            return new ResponseEntity<>(iCategoryService.save(category), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/category/display")
    public ResponseEntity<List<Category>> findAllCategory() {
        List<Category> categoryList = iCategoryService.findAll();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @PostMapping("/search/{name}")
    public ResponseEntity<List<Product>> findAllByNameProduct(@PathVariable String name) {
        List<Product> optionalProduct = iProductService.findAllByNameContaining( name);
        return new ResponseEntity<>(optionalProduct, HttpStatus.OK);
    }

    @PostMapping("/search/category/{name}")
    public ResponseEntity<List<Product>> findProductByCategory (@PathVariable String name) {
        List<Product> productPage = iProductService.findProductByCategoryContaining(name);
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }
}
