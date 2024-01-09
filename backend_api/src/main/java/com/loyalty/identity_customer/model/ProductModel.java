package com.loyalty.identity_customer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    @Column(name = "product_name")
    private String product_name;

    @Column(name = "price")
    private Long price;

    @Column(name = "brand_name")
    private String brand_name;

    @Column(name = "main_cate")
    private String main_cate;

    @Column(name = "sub_cate")
    private String sub_cate;

    @Column(name = "product_description")
    private String product_description;

    @Column(name = "unit")
    private String unit;

    @Column(name = "origin")
    private String origin;
    
    

}
