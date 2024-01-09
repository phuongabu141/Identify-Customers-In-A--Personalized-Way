package com.loyalty.identity_customer.serviceImpl;

import com.loyalty.identity_customer.model.CustomerModel;
import com.loyalty.identity_customer.model.LabelModel;
import com.loyalty.identity_customer.model.ResultModel;
import com.loyalty.identity_customer.repository.CustomerRepository;
import com.loyalty.identity_customer.repository.LabelRepository;
import com.loyalty.identity_customer.repository.ResultRepository;
import com.loyalty.identity_customer.request.ResultRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.response.ResultResponse;
import com.loyalty.identity_customer.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ResponseEntity<ApiResponse<Object>> getAllResult() {
        List<ResultResponse> listResult = resultRepository.getAllResult();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("Danh sách kết quả").data(listResult).build());
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> addResult(ResultRequest resultRequest) {
        Date date = new Date(System.currentTimeMillis());
        Optional<CustomerModel> customer = customerRepository.findById(resultRequest.getCustomer_id());
        Optional<LabelModel> label = labelRepository.findById(resultRequest.getLabel_id());
        ResultModel resultModel = ResultModel.builder().customerModel(customer.get()).labelModel(label.get()).logic_value(resultRequest.isLogic_value()).time(date).build();
        resultRepository.save(resultModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder().message("Tạo result thành công").data(resultModel).build());
    }
}
