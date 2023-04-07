package com.berat.controller;

import static com.berat.constant.EndPoint.*;

import com.berat.dto.request.CustomerCreateRequest;
import com.berat.dto.request.CustomerUpdateAddressRequest;
import com.berat.dto.request.CustomerUpdateRequest;
import com.berat.dto.response.CustomerResponse;
import com.berat.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(CUSTOMER)
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createCustomer(@RequestBody CustomerCreateRequest dto){
        return ResponseEntity.ok(customerService.createCustomer(dto));
    }
    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateCustomer(@RequestBody CustomerUpdateRequest dto){
        return ResponseEntity.ok(customerService.updateCustomer(dto));
    }
    @PutMapping(UPDATE+ADDRESS)
    public ResponseEntity<Boolean> updateAddress(@RequestBody CustomerUpdateAddressRequest dto){
        return ResponseEntity.ok(customerService.updateAddress(dto));
    }
    @DeleteMapping(DELETE+BYAUTHID)
    public ResponseEntity<Boolean> deleteByAuthId(@PathVariable Long authId){
        return ResponseEntity.ok(customerService.deleteByAuthId(authId));
    }
    @GetMapping(GETONE+BYCUSTOMERID)
    public ResponseEntity<CustomerResponse> getOneCustomerById(@PathVariable Long customerId){
        return ResponseEntity.ok(customerService.getOneCustomerById(customerId));
    }
}
