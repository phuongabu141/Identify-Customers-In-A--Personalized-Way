package com.loyalty.identity_customer.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
public class CustomerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "gender")
    private String gender;
    
    @Column(name = "birthday")
    private java.sql.Date birthday;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "province")
    private String province;
    
    @Column(name = "district")
    private String district;
    
    @Column(name = "created_time")
    private Date created_time;
    
    @Column(name = "is_delete")
    private boolean is_delete;
    
}
