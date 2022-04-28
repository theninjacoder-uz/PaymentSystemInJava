package com.example.paymentsysteminjava.entity.oson;

import com.example.paymentsysteminjava.entity.BaseEntity;
import com.example.paymentsysteminjava.entity.merchant.MerchantServiceEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OsonServiceEntity extends BaseEntity {

    private String name;
    private  String url;

    @OneToOne
    private MerchantServiceEntity merchantServiceEntity;


}
