package com.texxsupply.texxdimona.model.wordpress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Links {

    private List<Self> self;
    private List<Collection> collection;
    private List<Customer> customer;

}