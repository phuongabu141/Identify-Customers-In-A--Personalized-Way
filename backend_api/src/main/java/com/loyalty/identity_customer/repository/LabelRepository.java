package com.loyalty.identity_customer.repository;

import com.loyalty.identity_customer.model.LabelModel;
import com.loyalty.identity_customer.response.LabelResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface LabelRepository extends JpaRepository<LabelModel, Long> {

    @Query("SELECT DISTINCT NEW com.loyalty.identity_customer.response.LabelResponse(g.labelModel.id, l.label_name, l.status) FROM LabelModel l JOIN GroupModel g ON g.labelModel.id = l.id")
    List<LabelResponse> getLabel();

    // lấy chi tiết thông tin của 1 label theo id

    // lấy tất cả label
    @Query("SELECT NEW com.loyalty.identity_customer.response.LabelResponse(l.id, l.label_name, l.status) FROM LabelModel l")
    List<LabelResponse> getAllLabel();

    @Query("SELECT NEW com.loyalty.identity_customer.response.LabelResponse(l.id, l.label_name, l.status) FROM LabelModel l WHERE l.label_id = :labelId")
    LabelResponse getLabelById(@Param("labelId") Long labelId);

    @Query(value = "SELECT * FROM get_customer_groups(CAST(:customerId AS integer)) WHERE label_id = :labelId", nativeQuery = true)
    List<Map<String, Object>> listResultCustomerLabel(@Param("customerId") Long customerId, @Param("labelId") Long labelId);

    @Query("SELECT NEW com.loyalty.identity_customer.response.LabelResponse(l.id, l.label_name, l.status) FROM LabelModel l WHERE l.label_name LIKE %:name%")
    List<LabelResponse> findByName(@Param("name") String name);

    @Query("SELECT NEW com.loyalty.identity_customer.response.LabelResponse(l.id, l.label_name, l.status) FROM LabelModel l WHERE l.status = 'Đã kích hoạt'")
    List<LabelResponse> listLabelActivated();

    @Query("SELECT l FROM LabelModel l WHERE l.label_name = :name")
    LabelModel findByLabel_name(@Param("name") String name);
}
