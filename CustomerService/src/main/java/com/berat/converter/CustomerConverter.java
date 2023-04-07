package com.berat.converter;

import com.berat.dto.request.AddressCreateRequest;
import com.berat.dto.request.CustomerCreateRequest;
import com.berat.dto.response.CustomerResponse;
import com.berat.model.Address;
import com.berat.model.Customer;
import com.berat.util.Generator;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {

    public static Customer toCustomer(CustomerCreateRequest dto){
        return Customer.builder()
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .authId(dto.getAuthId())
                .customerNumber(Generator.randomCustomerNumber())
                .build();
    }
    public static CustomerResponse fromCustomer(Customer customer){
        return CustomerResponse.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .customerNumber(customer.getCustomerNumber())
                .address(customer.getAddress())
                .build();
    }
    public static Address toAddress(AddressCreateRequest dto,Customer customer){
        return Address.builder()
                .country(dto.getCountry())
                .city(dto.getCity())
                .description(dto.getDescription())
                .postalCode(dto.getPostalCode())
                .customer(customer)
                .build();
    }
}
