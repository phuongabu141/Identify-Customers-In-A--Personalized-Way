package com.loyalty.identity_customer.repository;

import com.loyalty.identity_customer.model.LabelEdgeModel;
import com.loyalty.identity_customer.response.LabelEdgeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabelEdgeRepository extends JpaRepository<LabelEdgeModel, Long> {

    @Query("SELECT new com.loyalty.identity_customer.response.LabelEdgeResponse(le.labelEdgeId.label_id, le.labelEdgeId.head_id) FROM LabelEdgeModel le")
    List<LabelEdgeResponse> getAllLabelEdge();
}
