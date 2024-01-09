package com.loyalty.identity_customer.serviceImpl;

import com.loyalty.identity_customer.model.LogicModel;
import com.loyalty.identity_customer.repository.LogicRepository;
import com.loyalty.identity_customer.request.LogicRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.service.LogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LogicServiceImpl implements LogicService {

    @Autowired
    private LogicRepository logicRepository;

    @Override
    public ResponseEntity<ApiResponse<Object>> addLogic(LogicRequest logicRequest)
    {
        LogicModel newLogic = LogicModel.builder().logic_name(logicRequest.getLogic_name()).description(logicRequest.getDescription()).notation(logicRequest.getNotation()).created_time(logicRequest.getCreated_date()).created_by(logicRequest.getCreated_by()).build();
        logicRepository.save(newLogic);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder().message("Thêm logic thành công").data(newLogic).build());
    }

}
