package com.berat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerNumber;
    private String email;
    private String phone;
    private Long authId;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
}
