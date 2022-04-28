package com.example.paymentsysteminjava.repository;

import com.example.paymentsysteminjava.entity.oson.OsonServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OsonServiceServiceRepository  extends JpaRepository<OsonServiceEntity , Long> {
}
