package com.loyalty.identity_customer.repository;

import com.loyalty.identity_customer.model.CustomerAttributeModel;
import com.loyalty.identity_customer.response.CustomerAttributeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerAttributeRepository extends JpaRepository<CustomerAttributeModel, Long> {

    @Query("SELECT DISTINCT NEW com.loyalty.identity_customer.response.CustomerAttributeResponse(cam.id.customerId, cam.id.attributeId, cam.value) FROM CustomerAttributeModel cam")
    List<CustomerAttributeResponse> getListCustomerAttribute();

    @Query("SELECT NEW com.loyalty.identity_customer.response.CustomerAttributeResponse(cam.id.customerId, cam.id.attributeId, cam.value) FROM CustomerAttributeModel cam")
    List<CustomerAttributeResponse> getAllCustomerAttribute();

    // lấy ra customer_attribute theo id khách hàng
    @Query("SELECT NEW com.loyalty.identity_customer.response.CustomerAttributeResponse(cam.id.customerId, cam.id.attributeId, cam.value) FROM CustomerAttributeModel cam WHERE cam.id.customerId = :customerId")
    List<CustomerAttributeResponse> getCustomerAttributeByCustomerId(@Param("customerId") Long customerId);
}
