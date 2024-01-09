package com.loyalty.identity_customer.serviceImpl;

import com.loyalty.identity_customer.model.UserModel;
import com.loyalty.identity_customer.repository.UserRepository;
import com.loyalty.identity_customer.request.LoginRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<ApiResponse<Object>> getAllUser()
    {
        List<UserModel> userModels = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("Lấy danh sách người dùng thành công").data(userModels).build());
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> loginWithPartner(LoginRequest loginRequest) {
        UserModel user = userRepository.getUserPartnerByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if(user == null)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.builder().message("Sai tai khoan hoac mat khau").build());
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("Đăng nhập thành công").data(user).build());
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> loginWithAdmin(LoginRequest loginRequest) {
        UserModel user = userRepository.getUserAdminByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if(user == null)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.builder().message("Sai tai khoan hoac mat khau").build());
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("Đăng nhập thành công").data(user).build());
    }
}
