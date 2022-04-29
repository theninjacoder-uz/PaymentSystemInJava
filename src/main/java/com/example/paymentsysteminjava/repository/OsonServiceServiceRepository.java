package com.example.paymentsysteminjava.repository;

import com.example.paymentsysteminjava.entity.oson.OsonServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;

@EnableJpaRepositories
public interface OsonServiceServiceRepository  extends JpaRepository<OsonServiceEntity , Long> {
//    @Query("select o from OsonServiceEntity o where upper(o.name) like upper(%:name%) ")
//    List<OsonServiceEntity> findByNameAllIgnoreCase(@Param("name") String name);

    @Query("SELECT m FROM OsonServiceEntity m WHERE m.name LIKE %:name%")
    List<OsonServiceEntity> searchByNameLike(@Param("name") String name);

//    List<OsonServiceEntity> findByNameContainingIgnoreCase(String name);
}
