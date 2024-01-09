package com.loyalty.identity_customer.controller;

import com.loyalty.identity_customer.request.GroupRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/group")
public class GroupController {

	@Autowired
	private GroupService groupService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ApiResponse<Object>> getGroupById(@PathVariable("id") Long idGroup)
	{
		return groupService.getGroupById(idGroup);
	}
	
	@GetMapping(value = "/all")
	public ResponseEntity<ApiResponse<Object>> getAllLogic()
	{
		return groupService.getAllGroup();
	}

	@GetMapping(value = "/ByLabel/{idLabel}")
	public ResponseEntity<ApiResponse<Object>> getListGroupByIdLabel(@PathVariable("idLabel") Long idLabel)
	{
		return groupService.getListGroupByIdLabel(idLabel);
	}

	@GetMapping(value = "/byLabel/{idLabel}")
	public ResponseEntity<ApiResponse<Object>> getListGroupByLabelId(@PathVariable("idLabel") Long idLabel)
	{
		return groupService.getListGroupByLabelId(idLabel);
	}

	@GetMapping(value = "/ConditionNotNull")
	public ResponseEntity<ApiResponse<Object>> getListGroupWithConditionNotNull()
	{
		return groupService.getListGroupWithConditionNotNull();
	}

//	@GetMapping(value = "/test")
//	public ResponseEntity<String> test()
//	{
//		return groupService.checkCondition();
//	}

	@PostMapping("/add")
	public ResponseEntity<ApiResponse<Object>> addGroup(@RequestBody GroupRequest groupRequest)
	{
		return groupService.addGroup(groupRequest);
	}
}
