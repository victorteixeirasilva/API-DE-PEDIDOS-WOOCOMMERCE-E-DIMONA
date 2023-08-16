package com.texxsupply.texxdimona.model.wordpress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Shipping {

    private String first_name;
    private String last_name;
    private String company;
    private String address_1;
    private String address_2;
    private String city;
    private String state;
    private String postcode;
    private String country;
    private String phone;
    private String number;
    private String neighborhood;

}