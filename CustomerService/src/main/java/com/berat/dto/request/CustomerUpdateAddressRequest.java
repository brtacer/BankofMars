package com.berat.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateAddressRequest {
    private Long customerId;
    private AddressCreateRequest addressCreateRequest;
}
