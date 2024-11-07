package com.example.Lab8_2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phone;

    public EmployeeRequest(String name, String email,String address, String phone){
        setEmail(email);
        setName(name);
        setPhone(phone);
        setAddress(address);
    }
}
