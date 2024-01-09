package com.loyalty.identity_customer.serviceImpl;

import com.loyalty.identity_customer.model.ProductModel;
import com.loyalty.identity_customer.repository.ProductRepository;
import com.loyalty.identity_customer.request.ProductRequest;
import com.loyalty.identity_customer.response.ApiResponse;
import com.loyalty.identity_customer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public ResponseEntity<ApiResponse<Object>> getAllProduct()
    {
        List<ProductModel> productModelList = productRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("lấy danh sách sản phẩm thành công").data(productModelList).build());
    }

    @Override
    public ResponseEntity<ApiResponse<Object>> addProduct(ProductRequest productRequest)
    {
        ProductModel newProductModel = ProductModel.builder().product_name(productRequest.getProduct_name()).price(productRequest.getPrice()).brand_name(productRequest.getBrand_name()).main_cate(productRequest.getMain_cate()).product_description(productRequest.getProduct_description()).unit(productRequest.getUnit()).origin(productRequest.getOrigin()).build();
//        KieContainer kieContainer = KieServices.get().newKieClasspathContainer();
//        KieSession session =kieContainer.newKieSession("product");
//        session.insert(newProductModel);
//        session.fireAllRules();
//        if(newProductModel.getPrice() == null)
//        {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.builder().message("Sản phẩm thêm vào có giá không quá 15000").build());
//        }
        productRepository.save(newProductModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder().message("Thêm sản phẩm mới thành công").data(newProductModel).build());
    }
}
