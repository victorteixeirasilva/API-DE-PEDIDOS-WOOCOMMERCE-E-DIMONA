package com.texxsupply.texxdimona.model.dimona;
import com.texxsupply.texxdimona.model.wordpress.OrderWorpress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Address {

    private String name;
    private String street;
    private String number;
    private String complement;
    private String city;
    private String state;
    private String zipcode;
    private String neighborhood;
    private String phone;
    private String country;

    public Address(OrderWorpress orderWorpress){
        this.name = orderWorpress.getShipping().getFirst_name()
                .concat(" ")
                .concat(orderWorpress.getShipping().getLast_name());
        this.street = orderWorpress.getShipping().getAddress_1();
        this.number = orderWorpress.getShipping().getNumber();
        this.complement = orderWorpress.getShipping().getAddress_2();
        this.city = orderWorpress.getShipping().getCity();
        this.state = orderWorpress.getShipping().getState();
        this.zipcode = orderWorpress.getShipping().getPostcode();
        this.neighborhood = orderWorpress.getShipping().getNeighborhood();
        this.phone = orderWorpress.getBilling().getCellphone();
        this.country = orderWorpress.getShipping().getCountry();
    }

}
