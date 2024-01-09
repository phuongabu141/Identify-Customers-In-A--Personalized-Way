package com.loyalty.identity_customer.repository;

import com.loyalty.identity_customer.model.GroupModel;
import com.loyalty.identity_customer.response.GroupResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<GroupModel, Long>{

    // group_id, head_gr_id, logic_id, {condition_id, attribute_id, operator_id, type, value}
	@Query("SELECT g FROM GroupModel g WHERE g.labelModel.label_id = :idLabel")
    List<GroupModel> getListGroupByIdLabel(@Param("idLabel") Long idLabel);

//    @Query("SELECT NEW com.loyalty.topic1.response.GroupResponse(g.group_id, g.head_group_id, g.logicModel.logic_id," +
//            " new com.loyalty.topic1.response.ConditionResponse(c.condition_id, c.attributeModel.attribute_id, c.operatorModel.operator_id, c.value))" +
//            " FROM GroupModel g LEFT JOIN g.conditionModel c WHERE g.labelModel.label_id = :idLabel")
//    @Query("SELECT NEW com.loyalty.topic1.response.GroupResponse(g.group_id, g.head_group_id," +
//            " new com.loyalty.topic1.response.LogicResponse(l.logic_id, l.notation)," +
//            " new com.loyalty.topic1.response.ConditionResponse(c.condition_id, c.attributeModel.attribute_id, c.operatorModel.operator_id, c.value))" +
//            " FROM GroupModel g LEFT JOIN g.logicModel l LEFT JOIN g.conditionModel c WHERE g.labelModel.label_id = :idLabel")
    @Query("SELECT NEW com.loyalty.identity_customer.response.GroupResponse(g.group_id, g.head_group_id," +
            " new com.loyalty.identity_customer.response.LogicResponse(l.logic_id, l.notation)," +
            " new com.loyalty.identity_customer.response.ConditionResponse(c.condition_id," +
            " new com.loyalty.identity_customer.response.AttributeResponse(a.attribute_id, a.name)," +
            " new com.loyalty.identity_customer.response.OperatorResponse(o.operator_id, o.notation), c.value)," +
            " new com.loyalty.identity_customer.response.LabelResponse(la.label_id, la.label_name, la.status))" +
            " FROM GroupModel g LEFT JOIN g.logicModel l LEFT JOIN g.labelModel la LEFT JOIN g.conditionModel c LEFT JOIN c.attributeModel a LEFT JOIN c.operatorModel o WHERE g.labelModel.label_id = :idLabel")
    List<GroupResponse> getListGroupByLabelId(@Param("idLabel") Long idLabel);

    @Query("SELECT NEW com.loyalty.identity_customer.response.GroupResponse(g.group_id, g.head_group_id," +
            " new com.loyalty.identity_customer.response.LogicResponse(l.logic_id, l.notation)," +
            " new com.loyalty.identity_customer.response.ConditionResponse(c.condition_id," +
            " new com.loyalty.identity_customer.response.AttributeResponse(a.attribute_id, a.name)," +
            " new com.loyalty.identity_customer.response.OperatorResponse(o.operator_id, o.notation), c.value), " +
            " new com.loyalty.identity_customer.response.LabelResponse(la.label_id, la.label_name, la.status))" +
            " FROM GroupModel g LEFT JOIN g.logicModel l LEFT JOIN g.labelModel la LEFT JOIN g.conditionModel c LEFT JOIN c.attributeModel a LEFT JOIN c.operatorModel o")
    List<GroupResponse> getAllGroup();

    @Query("SELECT g FROM GroupModel g WHERE g.conditionModel IS NOT NULL")
    List<GroupModel> getListGroupWithConditionNotNull();


    // lấy group by id
    @Query("SELECT NEW com.loyalty.identity_customer.response.GroupResponse(g.group_id, g.head_group_id," +
            " new com.loyalty.identity_customer.response.LogicResponse(l.logic_id, l.notation)," +
            " new com.loyalty.identity_customer.response.ConditionResponse(c.condition_id," +
            " new com.loyalty.identity_customer.response.AttributeResponse(a.attribute_id, a.name)," +
            " new com.loyalty.identity_customer.response.OperatorResponse(o.operator_id, o.notation), c.value)," +
            " new com.loyalty.identity_customer.response.LabelResponse(la.label_id, la.label_name, la.status))" +
            " FROM GroupModel g LEFT JOIN g.logicModel l LEFT JOIN g.labelModel la LEFT JOIN g.conditionModel c LEFT JOIN c.attributeModel a LEFT JOIN c.operatorModel o WHERE g.group_id = :groupId")
    GroupResponse getGroupById(@Param("groupId") Long groupId);
}
