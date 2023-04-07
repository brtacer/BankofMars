package com.berat.manager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.berat.constant.EndPoint.BYACCOUNTID;
import static com.berat.constant.EndPoint.EXIST;

@FeignClient(url = "http://localhost:6063/api/v1/account",decode404 = true,name = "card-account")
public interface IAccountManager {

    @GetMapping(EXIST+BYACCOUNTID)
    public ResponseEntity<Boolean> existByAccountId(@PathVariable Long accountId);
}
