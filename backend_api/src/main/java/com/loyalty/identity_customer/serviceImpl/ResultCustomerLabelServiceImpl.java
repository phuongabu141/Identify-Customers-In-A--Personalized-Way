package com.loyalty.identity_customer.serviceImpl;

import com.loyalty.identity_customer.repository.LabelRepository;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.response.ResultCustomerLabelResponse;
import com.loyalty.identity_customer.service.ResultCustomerLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class ResultCustomerLabelServiceImpl implements ResultCustomerLabelService {

    @Autowired
    private LabelRepository labelRepository;

    @Override
    public ResponseEntity<ApiResponse<Object>> getResultCustomerLabel(Long customerId, Long labelId) {
        List<Map<String, Object>> lists = labelRepository.listResultCustomerLabel(customerId, labelId);
        List<ResultCustomerLabelResponse> resultCustomerLabels = new ArrayList<>();
        for(Map<String, Object> item : lists)
        {
            ResultCustomerLabelResponse customerLabelResponse = ResultCustomerLabelResponse.builder()
                    .customer_id(getLongValue(item, "customer_id"))
                    .label_id(getLongValue(item, "label_id"))
                    .label_name(getStringValue(item, "label_name"))
                    .condition_id(getLongValue(item, "condition_id"))
                    .attribute_id(getLongValue(item, "attribute_id"))
                    .attribute_name(getStringValue(item, "attribute_name"))
                    .operator_id(getLongValue(item, "operator_id"))
                    .ope_notation(getStringValue(item, "ope_notation"))
                    .value(getStringValue(item, "value"))
                    .group_id(getLongValue(item, "group_id"))
                    .head_group_id(getLongValue(item, "head_group_id"))
                    .logic_id(getLongValue(item, "logic_id"))
                    .log_notation(getStringValue(item, "log_notation"))
                    .customer_value(getStringValue(item, "customer_value"))
                    .result_bool(getBooleanValue(item, "result_bool", false))
                    .build();
            resultCustomerLabels.add(customerLabelResponse);
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("Ket qua label cua khach hang").data(resultCustomerLabels).build());
    }


    // các hàm kiểm tra giá trị null trước khi map vào object
    private Long getLongValue(Map<String, Object> item, String key) {
        return Optional.ofNullable(item.get(key)).map(value -> Long.parseLong(value.toString())).orElse(null);
    }

    private Boolean getBooleanValue(Map<String, Object> item, String key, Boolean defaultValue) {
        return Optional.ofNullable(item.get(key)).map(value -> (Boolean) value).orElse(defaultValue);
    }

    private String getStringValue(Map<String, Object> item, String key) {
        return Optional.ofNullable(item.get(key)).map(Object::toString).orElse(null);
    }
}
