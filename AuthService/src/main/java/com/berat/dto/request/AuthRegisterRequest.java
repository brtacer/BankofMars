package com.berat.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRegisterRequest {

    private String identifyNumber;
    @Size(min = 3,max = 20,message = "Username must be min 3 and max 20 characters!")
    @NotNull(message = "Username can not be null!")
    private String username;
    private String phone;
    @Email
    private String email;
    private String password;
    private String repassword;
    @NotBlank
    private String role;
}
