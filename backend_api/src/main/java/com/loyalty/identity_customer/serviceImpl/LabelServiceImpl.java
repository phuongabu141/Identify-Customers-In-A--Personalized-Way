package com.loyalty.identity_customer.serviceImpl;

import com.loyalty.identity_customer.model.LabelEdgeId;
import com.loyalty.identity_customer.model.LabelEdgeModel;
import com.loyalty.identity_customer.model.LabelModel;
import com.loyalty.identity_customer.repository.LabelEdgeRepository;
import com.loyalty.identity_customer.repository.LabelRepository;
import com.loyalty.identity_customer.request.LabelRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.response.LabelDetailResponse;
import com.loyalty.identity_customer.response.LabelResponse;
import com.loyalty.identity_customer.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private LabelEdgeRepository labelEdgeRepository;

    @Override
    public ResponseEntity<ApiResponse<Object>> getLabelMapGroup() {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().data(labelRepository.getLabel()).build());
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> activeLabel(Long labelId) {
        Optional<LabelModel> labelModel = labelRepository.findById(labelId);
        labelModel.get().setStatus("Đã kích hoạt");
        labelRepository.save(labelModel.get());
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("Kích hoạt label thành công").data(labelModel.get()).build());
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> getAllLabel() {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("All label").data(labelRepository.getAllLabel()).build());
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> findByName(String labelName) {
        List<LabelResponse> listLabel = labelRepository.findByName(labelName);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("Danh sách label theo tên").data(listLabel).build());
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> findLabelActivated() {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("Danh sách label đã kích hoạt").data(labelRepository.listLabelActivated()).build());
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> addLabel(LabelRequest labelRequest) {
        LabelModel label = labelRepository.findByLabel_name(labelRequest.getLabel_name());
        Long head_label_id;
        if(label != null)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResponse.builder().message("Tên label đã tồn tại").build());
        }
        if(labelRequest.getHead_label_id() == null)
        {
            head_label_id = null;
        }
        else {
            head_label_id = labelRequest.getHead_label_id();
        }
        Date date = new Date(System.currentTimeMillis());
        LabelModel newLabel = LabelModel.builder().label_name(labelRequest.getLabel_name())
                .status("Nháp").created_time(date).description(labelRequest.getDescription()).build();
        labelRepository.save(newLabel);
        LabelEdgeModel newLabelEdge = new LabelEdgeModel();
        if(head_label_id != null)
        {
            LabelEdgeId labelEdgeId = LabelEdgeId.builder().label_id(newLabel.getLabel_id()).head_id(head_label_id).build();
            newLabelEdge = LabelEdgeModel.builder().labelEdgeId(labelEdgeId).created_time(date).is_delete(false).build();
            labelEdgeRepository.save(newLabelEdge);
        }
        LabelDetailResponse labelDetailResponse = LabelDetailResponse.builder().label_id(newLabel.getLabel_id())
                .label_name(newLabel.getLabel_name()).label_head_id(head_label_id)
                .created_time(date).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder().message("Label đã được tạo").data(labelDetailResponse).build());
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> getLabelById(Long labelId) {
        LabelResponse label = labelRepository.getLabelById(labelId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("Lable theo id").data(label).build());
    }
}
