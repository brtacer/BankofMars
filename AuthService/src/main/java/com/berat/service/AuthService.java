package com.berat.service;

import static com.berat.converter.AuthConverter.*;

import com.berat.dto.request.*;
import com.berat.dto.response.AuthResponse;
import com.berat.exception.AuthManagerException;
import com.berat.exception.ErrorType;
import com.berat.manager.ICustomerManager;
import com.berat.model.Auth;
import com.berat.model.Role;
import com.berat.model.Status;
import com.berat.rabbitmq.model.ApproveStatusMailModel;
import com.berat.rabbitmq.producer.ApproveStatusProducer;
import com.berat.repository.IAuthRepository;
import com.berat.util.JwtTokenManager;
import com.berat.util.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;
    private final ICustomerManager customerManager;
    private final ApproveStatusProducer approveStatusProducer;

    public AuthService(IAuthRepository authRepository, JwtTokenManager jwtTokenManager,
                       ICustomerManager customerManager, ApproveStatusProducer approveStatusProducer) {
        super(authRepository);
        this.authRepository = authRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.customerManager = customerManager;
        this.approveStatusProducer = approveStatusProducer;
    }

    public AuthResponse register(AuthRegisterRequest dto) {
        Auth auth = save(toAuth(dto));
        approveStatusProducer.sendActivationCode(ApproveStatusMailModel.builder()
                .email(auth.getEmail()).activationCode(auth.getActivationCode()).build());
        Optional<String> token = jwtTokenManager.createToken(auth.getId(), auth.getRole());
        if (token.isEmpty())
            throw new AuthManagerException(ErrorType.TOKEN_NOT_CREATED);
        if (auth.getRole().equals(Role.CUSTOMER))
            customerManager.createCustomer(toCustomerCreateRequest(auth));
        return toAuthResponse(auth.getId(), token.get());

    }

    public AuthResponse doLogin(AuthLoginRequest dto) {
        Optional<Auth> auth = authRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (auth.isEmpty())
            throw new AuthManagerException(ErrorType.INCORRECT_USERNAME_OR_PASSWORD);
        Optional<String> token = jwtTokenManager.createToken(auth.get().getId(), auth.get().getRole());
        if (token.isEmpty())
            throw new AuthManagerException(ErrorType.TOKEN_NOT_CREATED);
        return toAuthResponse(auth.get().getId(),token.get());
    }

    public Boolean approveStatus(ApproveStatusRequest dto) {
        Optional<Auth> auth = findById(dto.getId());
        if (auth.isEmpty())
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        if (!auth.get().getActivationCode().equals(dto.getActivationCode()))
            throw new AuthManagerException(ErrorType.INVALID_ACTIVATION_CODE);
        auth.get().setStatus(Status.APPROVED);
        update(auth.get());
        return true;
    }

    public Boolean updatePassword(AuthUpdatePasswordRequest dto) {
        Optional<Auth> auth = authRepository.findByPassword(dto.getPassword());
        if (auth.isEmpty())
            throw new AuthManagerException(ErrorType.INVALID_PASSWORD);
        auth.get().setPassword(dto.getNewPassword());
        update(auth.get());
        return true;
    }

    public Boolean blockByAuthId(Long authId) {
        Optional<Auth> auth = findById(authId);
        if (auth.isEmpty())
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        auth.get().setStatus(Status.BLOCKED);
        update(auth.get());
        return true;
    }

    public Boolean deleteByAuthId(Long authId) {
        Optional<Auth> auth = findById(authId);
        if (auth.isEmpty())
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        auth.get().setStatus(Status.DELETED);
        update(auth.get());
        return true;
    }

    public Boolean hardDeleteByAuthId(Long authId) {
        Optional<Auth> auth = findById(authId);
        if (auth.isEmpty())
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        delete(auth.get());
        if (auth.get().getRole().equals(Role.CUSTOMER))
            customerManager.deleteByAuthId(authId);
        return true;
    }

    public Boolean updateAuth(AuthUpdateRequest dto) {
        Optional<Auth> auth = findById(dto.getId());
        if (auth.isEmpty())
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        Auth toUpdate = auth.get();
        toUpdate.setEmail(dto.getEmail());
        toUpdate.setPhone(dto.getPhone());
        update(toUpdate);
        if (toUpdate.getRole().equals(Role.CUSTOMER))
            customerManager.updateCustomer(toCustomerUpdateRequest(toUpdate));
        return true;

    }

}
