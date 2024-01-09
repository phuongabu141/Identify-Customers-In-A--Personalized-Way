package com.loyalty.identity_customer.serviceImpl;


import com.google.protobuf.Api;
import com.loyalty.identity_customer.model.*;
import com.loyalty.identity_customer.repository.*;
import com.loyalty.identity_customer.request.CustomerLabelEventRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.response.LastCustomerLabelResponse;
import com.loyalty.identity_customer.service.CustomerLabelEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerLabelEventServiceImpl implements CustomerLabelEventService {

    @Autowired
    private CustomerLabelEventRepository customerLabelEventRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private EventTypeRepository eventTypeRepository;

    @Override
    public ResponseEntity<ApiResponse<Object>> getAllLastCustomerLabel() {
        List<Map<String, Object>> lists = customerLabelEventRepository.getAllLastCustomerLabel();
        List<LastCustomerLabelResponse> lastCustomerLabelList = new ArrayList<>();
        for(Map<String, Object> item : lists)
        {
            LastCustomerLabelResponse lastCustomerLabelEvent = LastCustomerLabelResponse.builder()
                    .customer_label_id(getLongValue(item, "customer_label_id"))
                    .customer_id(getLongValue(item, "customer_id"))
                    .created_time(getDateValue(item, "created_time"))
                    .label_id(getLongValue(item, "label_id"))
                    .last_event_id(getLongValue(item, "last_event_id"))
                    .is_delete(getBooleanValue(item, "is_delete", false))
                    .event_type_id(getLongValue(item, "event_type_id")).build();
            lastCustomerLabelList.add(lastCustomerLabelEvent);
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("Danh sách tất cả last customer label event").data(lastCustomerLabelList).build());
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> getLastCustomerLabelByCustomerId(Long customerId) {
        List<Map<String, Object>> lists = customerLabelEventRepository.getLastCustomerLabelByCustomerId(customerId);
        List<LastCustomerLabelResponse> lastCustomerLabelList = new ArrayList<>();
        for(Map<String, Object> item : lists)
        {
            LastCustomerLabelResponse lastCustomerLabelEvent = LastCustomerLabelResponse.builder()
                    .customer_label_id(getLongValue(item, "customer_label_id"))
                    .customer_id(getLongValue(item, "customer_id"))
                    .created_time(getDateValue(item, "created_time"))
                    .label_id(getLongValue(item, "label_id"))
                    .last_event_id(getLongValue(item, "last_event_id"))
                    .is_delete(getBooleanValue(item, "is_delete", false))
                    .event_type_id(getLongValue(item, "event_type_id")).build();
            lastCustomerLabelList.add(lastCustomerLabelEvent);
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("Last event của customer").data(lastCustomerLabelList).build());
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> addCustomerLabelEvent(CustomerLabelEventRequest customerLabelEventRequest) {
        Date date = new Date(System.currentTimeMillis());
        Optional<EventTypeModel> eventTypeModel = eventTypeRepository.findById(customerLabelEventRequest.getEvent_type_id());
        Optional<LabelModel> labelModel = labelRepository.findById(customerLabelEventRequest.getLabel_id());
        Optional<CustomerModel> customerModel = customerRepository.findById(customerLabelEventRequest.getCustomer_id());
        Optional<ResultModel> resultGroupModel = resultRepository.findById(customerLabelEventRequest.getResult_id());

        CustomerLabelEventModel customerLabelEventModel = CustomerLabelEventModel.builder().eventTypeModel(eventTypeModel.get())
                .labelModel(labelModel.get()).customerModel(customerModel.get())
                .resultModel(resultGroupModel.get()).created_time(date).is_delete(false).build();
        customerLabelEventRepository.save(customerLabelEventModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder().message("Tạo label event thành công").data(customerLabelEventModel).build());

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

    private Date getDateValue(Map<String, Object> item, String key) {
        return Optional.ofNullable(item.get(key)).map(value -> (Date) value).orElse(null);
    }

}
