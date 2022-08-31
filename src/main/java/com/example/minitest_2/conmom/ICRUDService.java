package com.example.minitest_2.conmom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICRUDService<E> {
    List<E> findAll();
    Optional<E> findById(Long id);
    E save(E e);
    void deleteById(Long id);
}
