package com.loyalty.identity_customer.repository;

import com.loyalty.identity_customer.model.CustomerModel;
import com.loyalty.identity_customer.response.CustomerIdResponse;
import com.loyalty.identity_customer.response.CustomerResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

    @Query("SELECT new com.loyalty.identity_customer.response.CustomerResponse(c.customer_id, c.name) FROM CustomerModel c")
    List<CustomerResponse> getAllCustomer();

    @Query("SELECT new com.loyalty.identity_customer.response.CustomerIdResponse(c.customer_id) FROM CustomerModel c WHERE c.customer_id = :customerId")
    CustomerIdResponse getCustomerById(@Param("customerId") Long customerId);
}
