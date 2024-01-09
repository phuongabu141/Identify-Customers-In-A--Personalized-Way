package com.loyalty.identity_customer.serviceImpl;

import com.loyalty.identity_customer.model.*;
import com.loyalty.identity_customer.repository.*;
import com.loyalty.identity_customer.request.GroupRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.response.CustomerAttributeResponse;
import com.loyalty.identity_customer.service.GroupService;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private CustomerAttributeRepository customerAttributeRepository;

    @Autowired
    private LogicRepository logicRepository;

    @Autowired
    private ConditionRepository conditionRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private OperatorRepository operatorRepository;

    @Override
    public ResponseEntity<ApiResponse<Object>> getGroupById(Long idLogic)
    {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("Lấy logic theo id").data(groupRepository.findById(idLogic)).build());
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> getAllGroup()
    {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("Tất cả group").data(groupRepository.getAllGroup()).build());
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> getListGroupByIdLabel(Long idLabel) {
        List<GroupModel> listGroupByIdLabel = groupRepository.getListGroupByIdLabel(idLabel);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("Danh sách group theo id label").data(listGroupByIdLabel).build());
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> getListGroupByLabelId(Long idLabel) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("Danh sách group theo label id").data(groupRepository.getListGroupByLabelId(idLabel)).build());
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> getListGroupWithConditionNotNull() {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("Danh sách group có condition").data(groupRepository.getListGroupWithConditionNotNull()).build());
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> addGroup(GroupRequest groupRequest) {
        LogicModel logicModel = new LogicModel();
        AttributeModel attributeModel = new AttributeModel();
        OperatorModel operatorModel = new OperatorModel();
        ConditionModel conditionModel = new ConditionModel();
        Long head_group_id;
        Date date = new Date(System.currentTimeMillis());
        if(groupRequest.getHead_group_id() == null)
        {
            head_group_id = null;
        }
        else{
            head_group_id = groupRequest.getHead_group_id();
        }
        if(groupRequest.getLogic_id() == null)
        {
            logicModel = null;
        }
        else
        {
            logicModel = logicRepository.findLogicById(groupRequest.getLogic_id());
        }
        if(groupRequest.getConditionRequest().getAttribute_id() == null || groupRequest.getConditionRequest().getOperator_id() == null)
        {
            attributeModel = null;
            operatorModel = null;
            conditionModel = null;
        }
        else
        {
            attributeModel = attributeRepository.findAttributeById(groupRequest.getConditionRequest().getAttribute_id());
            operatorModel = operatorRepository.findOperatorById(groupRequest.getConditionRequest().getOperator_id());
            conditionModel = ConditionModel.builder().attributeModel(attributeModel).operatorModel(operatorModel).value(groupRequest.getConditionRequest().getValue()).created_time(date).is_delete(false).build();
            conditionRepository.save(conditionModel);
        }
        Optional<LabelModel> label = labelRepository.findById(groupRequest.getLabel_id());
        GroupModel newGroup = GroupModel.builder().group_id(groupRequest.getGroup_id()).head_group_id(head_group_id)
                .logicModel(logicModel).conditionModel(conditionModel).labelModel(label.get())
                .created_time(date).is_delete(false).build();
        groupRepository.save(newGroup);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder().message("Tạo group thành công").data(newGroup).build());
    }

//    boolean checkCondition(String customerAttributeValue, OperatorModel operatorModel, String conditionValue)
//    {
//        double customerAttributeValueDouble = Double.parseDouble(customerAttributeValue);
//        double conditionValueDouble = Double.parseDouble(conditionValue);
//
//        return switch (operatorModel.getNotation()) {
//            case ">" -> customerAttributeValueDouble > conditionValueDouble;
//            case "<" -> customerAttributeValueDouble < conditionValueDouble;
//            case "=" -> customerAttributeValueDouble == conditionValueDouble;
//            case ">=" -> customerAttributeValueDouble >= conditionValueDouble;
//            case "<=" -> customerAttributeValueDouble <= conditionValueDouble;
//            default -> false;
//        };
//    }


//    @Override
//    public ResponseEntity<String> checkCondition()
//    {
//        long startTime = System.currentTimeMillis();
//        List<GroupModel> groupModels = groupRepository.getListGroupWithConditionNotNull();
//        List<CustomerAttributeResponse> customerAttributeModels = customerAttributeRepository.getAllCustomerAttribute();
//        //List<CustomerAttributeModel> customerAttributeModels = customerAttributeRepository.findAll();
//        long endTime = System.currentTimeMillis();
//
//        // Tính thời gian chạy
//        long executionTime = endTime - startTime;
//        System.out.println("Thời gian đọc db: " + executionTime + " milliseconds");
//        KieContainer kieContainer = KieServices.get().newKieClasspathContainer();
//        KieSession session = kieContainer.newKieSession("label");
//        for (CustomerAttributeResponse customerAttribute : customerAttributeModels) {
//            session.insert(customerAttribute);
//        }
//        for (GroupModel group : groupModels) {
//            session.insert(group);
//        }
//        session.fireAllRules();
//        session.dispose();
//        // Kết thúc đo thời gian
//        return ResponseEntity.status(HttpStatus.OK).body("đã chạy");
//    }

}
