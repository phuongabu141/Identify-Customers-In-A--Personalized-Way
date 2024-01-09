package com.loyalty.identity_customer.repository;

import com.loyalty.identity_customer.model.ResultGroupModel;
import com.loyalty.identity_customer.response.ResultGroupResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ResultGroupRepository extends JpaRepository<ResultGroupModel, Long> {

    @Query(value = "SELECT * FROM select_result_group(CAST(:resultId AS integer))", nativeQuery = true)
    List<Map<String, Object>> listResultGroupByIdResult(@Param("resultId") Long resultId);
}
