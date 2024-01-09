package com.loyalty.identity_customer.serviceImpl;

import com.loyalty.identity_customer.model.CustomerLabelEventModel;
import com.loyalty.identity_customer.model.CustomerLabelModel;
import com.loyalty.identity_customer.model.CustomerModel;
import com.loyalty.identity_customer.model.LabelModel;
import com.loyalty.identity_customer.repository.CustomerLabelEventRepository;
import com.loyalty.identity_customer.repository.CustomerLabelRepository;
import com.loyalty.identity_customer.repository.CustomerRepository;
import com.loyalty.identity_customer.repository.LabelRepository;
import com.loyalty.identity_customer.request.CustomerLabelRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.response.CustomerLabelResponse;
import com.loyalty.identity_customer.response.CustomerResponse;
import com.loyalty.identity_customer.response.LabelResponse;
import com.loyalty.identity_customer.service.CustomerLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerLabelServiceImpl implements CustomerLabelService {

    @Autowired
    private CustomerLabelRepository customerLabelRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private CustomerLabelEventRepository customerLabelEventRepository;

    @Override
    public ResponseEntity<ApiResponse<Object>> getListCustomerByLabelId(Long labelId) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("Danh sách khách hàng đã được gán label").data(customerLabelRepository.getListCustomerByLabelId(labelId)).build());
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> listCustomerByLabelId(List<Long> listLabelId) {
        List<CustomerLabelResponse> listCustomerLabel = new ArrayList<>();
        for(Long item : listLabelId)
        {
            LabelResponse label = labelRepository.getLabelById(item);
            List<CustomerResponse> customerResponseList = customerLabelRepository.getListCustomerByLabelId(item);
            for (CustomerResponse item2 : customerResponseList)
            {   CustomerLabelResponse customerLabel = CustomerLabelResponse.builder().label_name(label.getLabel_name()).customerResponse(item2).build();
                listCustomerLabel.add(customerLabel);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("Danh sách khách hàng").data(listCustomerLabel).build());
    }


    @Override
    public ResponseEntity<ApiResponse<Object>> addCustomerLabel(CustomerLabelRequest customerLabelRequest) {
        Optional<CustomerModel> customerModel = customerRepository.findById(customerLabelRequest.getCustomer_id());
        Optional<LabelModel> labelModel = labelRepository.findById(customerLabelRequest.getLabel_id());
        Optional<CustomerLabelEventModel> customerLabelEventModel = customerLabelEventRepository.findById(customerLabelRequest.getLast_event_id());
        Date date = new Date(System.currentTimeMillis());
        CustomerLabelModel customerLabelModel = CustomerLabelModel.builder().customerModel(customerModel.get())
                .labelModel(labelModel.get()).customerLabelEventModel(customerLabelEventModel.get()).created_time(date).is_delete(false).build();
        customerLabelRepository.save(customerLabelModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder().message("Tạo customer label thành công").data(customerLabelModel).build());
    }
}
