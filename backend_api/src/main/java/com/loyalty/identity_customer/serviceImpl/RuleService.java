package com.loyalty.identity_customer.serviceImpl;

import com.loyalty.identity_customer.repository.CustomerAttributeRepository;
import com.loyalty.identity_customer.repository.CustomerRepository;
import com.loyalty.identity_customer.repository.GroupRepository;
import com.loyalty.identity_customer.repository.LabelRepository;
import com.loyalty.identity_customer.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RuleService {

    @Autowired
    private CustomerAttributeRepository customerAttributeRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LabelRepository labelRepository;

    public ResponseEntity<String> ruleForAllCustomer(Long labelId){
        List<CustomerResponse> listCustomer = customerRepository.getAllCustomer();
        LabelResponse label = labelRepository.getLabelById(labelId);
        System.out.println(label.getLabel_id());
        for(CustomerResponse customer : listCustomer)
        {
            if(customer.getCustomer_id() == 186)
            {
                List<CustomerAttributeResponse> customerAttributes = customerAttributeRepository.getCustomerAttributeByCustomerId(customer.getCustomer_id());
                    List<GroupResponse> groups = groupRepository.getListGroupByLabelId(label.getLabel_id());
                    if(groups.isEmpty())
                        System.out.println("Label chua nam trong group nao");
                    else {
                        GroupResponse root = findRoot(groups);
                        if(isMatchGroup(customerAttributes, root, groups))
                        {
                            System.out.println("Khach hang " + customer.getCustomer_id() + " thoa group " + root.getGroup_id());
                        }
                        else {
                            System.out.println("=> customer " + customer.getCustomer_id() + " KHONG thoa " + "group " + root.getGroup_id());
                        }
                    }

            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("da chay rule");
    }

    public boolean isMatchGroup(List<CustomerAttributeResponse> customerAttributes, GroupResponse currentGroup, List<GroupResponse> groupList)
    {
        System.out.println("-----" + currentGroup);
        boolean result;
        if(currentGroup.getConditionResponse().getCondition_id() != null)
        {
            result = false;
            System.out.println("condition: " + currentGroup.getConditionResponse());
            String currentValue = "";
            for(CustomerAttributeResponse customerAttribute : customerAttributes)
            {
                if(customerAttribute.getAttribute_id() == currentGroup.getConditionResponse().getAttributeResponse().getAttribute_id())
                {
                    currentValue = customerAttribute.getValue();
                    System.out.println("value is " + currentValue );
                }
            }
            String operatorNotation = currentGroup.getConditionResponse().getOperatorResponse().getNotation();
            String value = currentGroup.getConditionResponse().getValue();
            result = checkCondition(currentValue, operatorNotation, value);
            System.out.println(currentValue + " " + currentGroup.getConditionResponse().getOperatorResponse().getNotation() + " " + value + "->" + result);
        }
        else
        {
            result = true;
            // check logic node
            System.out.println("logic " + currentGroup.getLogicResponse().getLogic_notation());
            List<GroupResponse> children = findChildren(currentGroup, groupList);

            for(GroupResponse group : children)
            {
                System.out.println("children of " + currentGroup.getGroup_id() + " is "+ group.getGroup_id());
            }
            if(currentGroup.getLogicResponse().getLogic_id() == 2)
            {
                result = children.stream().allMatch(child -> isMatchGroup(customerAttributes, child, groupList));
                System.out.println("rs logic and node " + currentGroup.getGroup_id() + " - " + result);
            }
            else if(currentGroup.getLogicResponse().getLogic_id() == 5)
            {
                result = children.stream().anyMatch(child -> isMatchGroup(customerAttributes, child, groupList));
                System.out.println("rs logic or node " + currentGroup.getGroup_id() + " - " + result);
            }
        }
        return result;
    }

    public boolean checkCondition(String attributeValue, String operatorNotation, String conditionValue) {
        if (attributeValue == null) {
            return false;
        }
        if(conditionValue == null)
        {
            return false;
        }

        try {
            double attributeDouble = Double.parseDouble(attributeValue);
            double conditionDouble = Double.parseDouble(conditionValue);

            switch (operatorNotation) {
                case ">":
                    return attributeDouble > conditionDouble;
                case "<":
                    return attributeDouble < conditionDouble;
                case "=":
                    return attributeValue.equals(conditionValue);
                case ">=":
                    return attributeDouble >= conditionDouble;
                case "<=":
                    return attributeDouble <= conditionDouble;
                default:
                    throw new IllegalArgumentException("Unsupported operator: " + operatorNotation);
            }
        } catch (NumberFormatException e) {
            // Xử lý nếu không thể chuyển đổi thành số
            return false;
        }
    }

    public GroupResponse findRoot(List<GroupResponse> groups) {
        return groups.stream().filter(group -> group.getHead_group_id() == null).findFirst().orElse(null);
    }

    public List<GroupResponse> findChildren(GroupResponse group, List<GroupResponse> groups) {
        return groups.stream().filter(child -> child.getHead_group_id() == group.getGroup_id()).collect(Collectors.toList());
    }
}
