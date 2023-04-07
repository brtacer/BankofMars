package com.berat.controller;

import com.berat.dto.request.*;
import com.berat.dto.response.AuthResponse;
import com.berat.exception.AuthManagerException;
import com.berat.exception.ErrorType;
import com.berat.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.berat.constant.EndPoint.*;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(REGISTER)
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthRegisterRequest dto) {
        if (!dto.getPassword().equals(dto.getRepassword()))
            throw new AuthManagerException(ErrorType.PASSWORD_AND_REPASSWORD_NOT_MATCH);
        return ResponseEntity.ok(authService.register(dto));
    }
    @PostMapping(LOGIN)
    public ResponseEntity<AuthResponse> doLogin(@RequestBody AuthLoginRequest dto) {
        return ResponseEntity.ok(authService.doLogin(dto));
    }
    @PostMapping(APPROVE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Boolean> approveStatus(@RequestBody ApproveStatusRequest dto){
        return ResponseEntity.ok(authService.approveStatus(dto));
    }
    @PostMapping(UPDATE+PASSWORD)
    public ResponseEntity<Boolean> updatePassword(@RequestBody AuthUpdatePasswordRequest dto){
        if (!dto.getNewPassword().equals(dto.getRepassword()))
            throw new AuthManagerException(ErrorType.PASSWORD_AND_REPASSWORD_NOT_MATCH);
        return ResponseEntity.ok(authService.updatePassword(dto));
    }
    @PatchMapping(BLOCK+ BYAUTHID)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Boolean> blockByAuthId(@PathVariable Long authId){
        return ResponseEntity.ok(authService.blockByAuthId(authId));
    }
    @PatchMapping(DELETE+ BYAUTHID)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Boolean> deleteByAuthId(@PathVariable Long authId){
        return ResponseEntity.ok(authService.deleteByAuthId(authId));
    }
    @DeleteMapping(DELETE+ BYAUTHID)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Boolean> hardDeleteByAuthId(@PathVariable Long authId){
        return ResponseEntity.ok(authService.hardDeleteByAuthId(authId));
    }
    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateAuth(@RequestBody @Valid AuthUpdateRequest dto){
        return ResponseEntity.ok(authService.updateAuth(dto));
    }

}
