package com.loyalty.identity_customer.repository;

import com.loyalty.identity_customer.model.CustomerLabelModel;
import com.loyalty.identity_customer.response.CustomerResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CustomerLabelRepository extends JpaRepository<CustomerLabelModel, Long> {

    @Query("SELECT new com.loyalty.identity_customer.response.CustomerResponse(cl.customerModel.customer_id, cl.customerModel.name, cl.customerModel.gender, cl.customerModel.birthday, cl.customerModel.email, cl.customerModel.phone, cl.customerModel.province, cl.customerModel.district) FROM CustomerLabelModel cl WHERE cl.labelModel.label_id = :labelId AND cl.is_delete = false")
    List<CustomerResponse> getListCustomerByLabelId(@Param("labelId") Long labelId);

    @Query(value = "SELECT * FROM select_root_group()", nativeQuery = true)
    List<Map<String, Object>> listResultGroupIsTrue();

    @Query("SELECT cm FROM CustomerLabelModel cm WHERE cm.customerModel.customer_id = :customerId AND cm.labelModel.label_id = :labelId")
    CustomerLabelModel findByCustomerIdAndLabelId(@Param("customerId") Long customerId, @Param("labelId") Long labelId);
}
