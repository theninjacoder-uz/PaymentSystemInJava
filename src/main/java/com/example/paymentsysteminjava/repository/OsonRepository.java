package com.example.paymentsysteminjava.repository;

import com.example.paymentsysteminjava.entity.oson.OsonServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OsonRepository extends JpaRepository<OsonServiceEntity, Long> {
    Optional<OsonServiceEntity> searchAllByNameContains(String s);
}
