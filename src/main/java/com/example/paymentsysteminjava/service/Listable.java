package com.example.paymentsysteminjava.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Objects;

public abstract class Listable<T extends JpaRepository> {
    private static final int DEFAULT_SIZE = 12;
    private static final int DEFAULT_PAGE = 0;
    private static final Sort.Direction direction = Sort.Direction.DESC;

    public Page list(T t, Integer page, Integer size , String direction, String... properties) {
//        if(direction != null && properties != null)
            return t.findAll(PageRequest.of(
                    page != null ? DEFAULT_PAGE : page,
                    size != null ? DEFAULT_SIZE : size,
                    Sort.Direction.fromString(direction),
                    properties
            ));
    }

}
