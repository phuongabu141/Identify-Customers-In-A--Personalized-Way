package com.loyalty.identity_customer.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private T data;

//    private boolean status;

    private String message;
}
