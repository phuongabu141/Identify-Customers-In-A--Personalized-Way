package com.loyalty.identity_customer.repository;

import com.loyalty.identity_customer.model.ResultModel;
import com.loyalty.identity_customer.response.ResultResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<ResultModel, Long> {
    @Query("SELECT NEW com.loyalty.identity_customer.response.ResultResponse(rm.result_id," +
            " NEW com.loyalty.identity_customer.response.CustomerResponse(cm.customer_id, cm.name, cm.gender, cm.birthday, cm.email, cm.phone, cm.province, cm.district)," +
            " NEW com.loyalty.identity_customer.response.LabelResponse(l.id, l.label_name, l.status), rm.logic_value, rm.time) FROM ResultModel rm LEFT JOIN rm.customerModel cm LEFT JOIN rm.labelModel l")
    List<ResultResponse> getAllResult();
}
