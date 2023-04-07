package com.berat.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUpdatePasswordRequest {
    private Long id;
    private String password;
    private String newPassword;
    private String repassword;
}
