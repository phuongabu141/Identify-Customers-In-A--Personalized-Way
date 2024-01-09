package com.loyalty.identity_customer.serviceImpl;

import com.loyalty.identity_customer.model.*;
import com.loyalty.identity_customer.repository.CustomerRepository;
import com.loyalty.identity_customer.repository.GroupRepository;
import com.loyalty.identity_customer.repository.LabelRepository;
import com.loyalty.identity_customer.repository.ResultGroupRepository;
import com.loyalty.identity_customer.request.ResultGroupRequest;
import com.loyalty.identity_customer.response.*;
import com.loyalty.identity_customer.service.ResultGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ResultGroupServiceImpl implements ResultGroupService {
    @Autowired
    private ResultGroupRepository resultGroupRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public ResponseEntity<ApiResponse<Object>> getResultGroupByResultId(Long resultId) {
        List<Map<String, Object>> listResultGroup = resultGroupRepository.listResultGroupByIdResult(resultId);
        List<ResultGroupResponse> resultGroupList = new ArrayList<>();
        for(Map<String, Object> item : listResultGroup)
        {
            GroupResponse group = groupRepository.getGroupById(getLongValue(item, "group_id"));
            ResultGroupResponse resultGroup = ResultGroupResponse.builder().result_id(getLongValue(item, "result_id"))
                    .groupResponse(group).logic_value(getBooleanValue(item, "logic_value", false)).customer_value(getStringValue(item, "customer_value")).build();
            resultGroupList.add(resultGroup);
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("Result Group theo group ID").data(resultGroupList).build());
    }

    private Long getLongValue(Map<String, Object> item, String key) {
        return Optional.ofNullable(item.get(key)).map(value -> Long.parseLong(value.toString())).orElse(null);
    }

    private Boolean getBooleanValue(Map<String, Object> item, String key, Boolean defaultValue) {
        return Optional.ofNullable(item.get(key)).map(value -> (Boolean) value).orElse(defaultValue);
    }

    private String getStringValue(Map<String, Object> item, String key) {
        return Optional.ofNullable(item.get(key)).map(Object::toString).orElse(null);
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> addResultGroup(ResultGroupRequest resultGroupRequest) {
        Long resultId = resultGroupRequest.getResult_id();
        Long groupId = resultGroupRequest.getGroup_id();
        ResultGroupId newResultGroupId = ResultGroupId.builder().result_id(resultId).group_id(groupId).build();
        ResultGroupModel newResultGroup = ResultGroupModel.builder().resultGroupId(newResultGroupId).logic_value(resultGroupRequest.is_true()).customer_value(resultGroupRequest.getCustomer_value()).build();
        resultGroupRepository.save(newResultGroup);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder().message("Tạo Result Group thành công").data(newResultGroup).build());
    }
}
