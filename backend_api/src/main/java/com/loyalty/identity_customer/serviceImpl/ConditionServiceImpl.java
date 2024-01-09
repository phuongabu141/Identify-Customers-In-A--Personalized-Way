package com.loyalty.identity_customer.serviceImpl;

import com.loyalty.identity_customer.model.AttributeModel;
import com.loyalty.identity_customer.model.ConditionModel;
import com.loyalty.identity_customer.model.OperatorModel;
import com.loyalty.identity_customer.repository.AttributeRepository;
import com.loyalty.identity_customer.repository.ConditionRepository;
import com.loyalty.identity_customer.repository.OperatorRepository;
import com.loyalty.identity_customer.request.ConditionRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.service.ConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ConditionServiceImpl implements ConditionService {

    @Autowired
    private ConditionRepository conditionRepository;

    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private OperatorRepository operatorRepository;

    @Override
    public ResponseEntity<ApiResponse<Object>> getConditionById(Long idCondition) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("Condition By Id").data(conditionRepository.getConditionById(idCondition)).build());
    }

//    @Override
//    public ResponseEntity<ApiResponse<Object>> addCondition(ConditionRequest conditionRequest) {
//        Optional<AttributeModel> attributeModel = attributeRepository.findById(conditionRequest.getAttribute_id());
//        Optional<OperatorModel> operatorModel = operatorRepository.findById(conditionRequest.getOperator_id());
//        if(attributeModel.isEmpty())
//        {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.builder().message("Attribute không tồn tại").build());
//        }
//        if(operatorModel.isEmpty())
//        {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.builder().message("Operator không tồn tại").build());
//        }
//        Date date = new Date(System.currentTimeMillis());
//        ConditionModel newConditionModel = ConditionModel.builder().attributeModel(attributeModel.get()).operatorModel(operatorModel.get()).value(conditionRequest.getValue()).created_time(date).is_delete(false).build();
//        conditionRepository.save(newConditionModel);
//        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder().message("Tạo condition thành công").data(newConditionModel).build());
//    }
}
