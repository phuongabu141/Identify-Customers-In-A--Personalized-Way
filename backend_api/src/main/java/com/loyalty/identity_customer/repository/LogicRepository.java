package com.loyalty.identity_customer.repository;

import com.loyalty.identity_customer.model.LogicModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface LogicRepository extends JpaRepository<LogicModel, Long>{
	
	@Query("SELECT l FROM LogicModel l WHERE l.logic_id = :id")
    LogicModel findLogicById(@Param("id") Long id);
}
