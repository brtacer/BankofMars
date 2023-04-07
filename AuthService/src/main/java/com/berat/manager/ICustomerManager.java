package com.berat.manager;

import com.berat.dto.request.CustomerCreateRequest;
import com.berat.dto.request.CustomerUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.berat.constant.EndPoint.*;
import static com.berat.constant.EndPoint.BYCUSTOMERID;

@FeignClient(url = "http://localhost:6062/api/v1/customer",decode404 = true,name = "auth-customer")
public interface ICustomerManager {

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createCustomer(@RequestBody CustomerCreateRequest dto);
    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateCustomer(@RequestBody CustomerUpdateRequest dto);
    @DeleteMapping(DELETE+BYAUTHID)
    public ResponseEntity<Boolean> deleteByAuthId(@PathVariable Long authId);
}
