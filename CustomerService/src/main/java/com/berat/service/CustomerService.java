package com.berat.service;

import static com.berat.converter.CustomerConverter.*;
import com.berat.dto.request.CustomerCreateRequest;
import com.berat.dto.request.CustomerUpdateAddressRequest;
import com.berat.dto.request.CustomerUpdateRequest;
import com.berat.dto.response.CustomerResponse;
import com.berat.exception.CustomerManagerException;
import com.berat.exception.ErrorType;
import com.berat.model.Customer;
import com.berat.repository.ICustomerRepository;
import com.berat.util.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService extends ServiceManager<Customer,Long> {
    private final ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository) {
        super(customerRepository);
        this.customerRepository = customerRepository;
    }

    public Boolean createCustomer(CustomerCreateRequest dto) {
        try {
            save(toCustomer(dto));
        }catch (Exception exception){
            return false;
        }
        return true;
    }

    public Boolean updateCustomer(CustomerUpdateRequest dto) {
        Optional<Customer> customer = customerRepository.findByAuthId(dto.getAuthId());
        if (customer.isEmpty())
            throw new CustomerManagerException(ErrorType.CUSTOMER_NOT_FOUND);
        Customer toUpdate = customer.get();
        toUpdate.setEmail(dto.getEmail());
        toUpdate.setPhone(dto.getPhone());
        update(toUpdate);
        return true;
    }

    public Boolean deleteByAuthId(Long authId) {
        Optional<Customer> customer = customerRepository.findByAuthId(authId);
        if (customer.isEmpty())
            throw new CustomerManagerException(ErrorType.CUSTOMER_NOT_FOUND);
        delete(customer.get());
        return true;
    }

    public CustomerResponse getOneCustomerById(Long customerId) {
        return fromCustomer(findById(customerId)
                .orElseThrow(()-> new CustomerManagerException(ErrorType.CUSTOMER_NOT_FOUND)));
    }

    public Boolean updateAddress(CustomerUpdateAddressRequest dto) {
        Optional<Customer> customer = findById(dto.getCustomerId());
        if (customer.isEmpty())
            throw new CustomerManagerException(ErrorType.CUSTOMER_NOT_FOUND);
        customer.get().setAddress(toAddress(dto.getAddressCreateRequest(),customer.get()));
        update(customer.get());
        return true;
    }
}
