package com_selfProject_UserService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {
    private String streetName;
    private String houseNo;
    private String city;
    private int pinCode;
    private String state;
}
