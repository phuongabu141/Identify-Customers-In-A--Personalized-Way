package com.loyalty.identity_customer.serviceImpl;

import com.loyalty.identity_customer.model.PartnerRequestModel;
import com.loyalty.identity_customer.repository.PartnerRequestRepository;
import com.loyalty.identity_customer.request.PartnerRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.service.PartnerRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PartnerRequestImpl implements PartnerRequestService {

    @Autowired
    private PartnerRequestRepository partnerRequestRepository;


    @Override
    public ResponseEntity<ApiResponse<Object>> getAllPartnerRequest() {
        List<PartnerRequestModel> list = partnerRequestRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("Danh sách yêu cầu").data(list).build());
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> addPartnerRequest(PartnerRequest partnerRequest) {
        Date date = new Date(System.currentTimeMillis());
        PartnerRequestModel partnerRequestModel = PartnerRequestModel.builder().label_name(partnerRequest.getLabel_name()).description(partnerRequest.getDescription())
                .status("Chờ duyệt").created_time(date).is_delete(false).type("Đề xuất label").build();
        partnerRequestRepository.save(partnerRequestModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder().message("Tạo yêu cầu đề xuất thành công").data(partnerRequestModel).build());

    }
}
