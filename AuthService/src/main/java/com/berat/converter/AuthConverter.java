package com.berat.converter;

import com.berat.dto.request.AuthRegisterRequest;
import com.berat.dto.request.CustomerCreateRequest;
import com.berat.dto.request.CustomerUpdateRequest;
import com.berat.dto.response.AuthResponse;
import com.berat.model.Auth;
import com.berat.model.Role;
import com.berat.util.Generator;
import org.springframework.stereotype.Component;

@Component
public class AuthConverter {

    public static Auth toAuth(AuthRegisterRequest dto){
        return Auth.builder()
                .identifyNumber(dto.getIdentifyNumber())
                .username(dto.getUsername())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(Role.valueOf(dto.getRole()))
                .activationCode(Generator.randomActivationCode())
                .build();
    }
    public static AuthResponse toAuthResponse(Long id, String token){
        return AuthResponse.builder()
                .id(id)
                .token(token)
                .build();
    }
    public static CustomerCreateRequest toCustomerCreateRequest(Auth auth){
        return CustomerCreateRequest.builder()
                .email(auth.getEmail())
                .phone(auth.getPhone())
                .authId(auth.getId())
                .build();
    }
    public static CustomerUpdateRequest toCustomerUpdateRequest(Auth auth){
        return CustomerUpdateRequest.builder()
                .authId(auth.getId())
                .email(auth.getEmail())
                .phone(auth.getPhone())
                .build();
    }
}
