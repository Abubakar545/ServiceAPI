package com.syed.paymentapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserRegistrationResponseDTO {

    private String statusCode;
    private String statusMsg;
    private String accessToken;

}
