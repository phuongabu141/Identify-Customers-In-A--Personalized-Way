package com.loyalty.identity_customer.repository;

import com.loyalty.identity_customer.model.AttributeModel;
import com.loyalty.identity_customer.response.AttributeDetailResponse;
import com.loyalty.identity_customer.response.AttributeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeRepository extends JpaRepository<AttributeModel, Long> {

    @Query("SELECT NEW com.loyalty.identity_customer.response.AttributeDetailResponse(am.attribute_id, am.name, am.start_date, am.end_date) FROM AttributeModel am")
    List<AttributeDetailResponse> getAllAttribute();

    @Query("SELECT am FROM AttributeModel am WHERE am.attribute_id = :id")
    AttributeModel findAttributeById(@Param("id") Long id);
}
