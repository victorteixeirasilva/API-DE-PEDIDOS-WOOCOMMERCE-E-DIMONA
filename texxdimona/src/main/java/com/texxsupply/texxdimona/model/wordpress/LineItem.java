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
public class LineItem {

    private Integer id;
    private String name;
    private Integer product_id;
    private Integer variation_id;
    private Integer quantity;
    private String taxClass;
    private String subtotal;
    private String subtotalTax;
    private String total;
    private String totalTax;
    private List<Object> taxes;
    private List<MetaDatum__1> meta_data;
    private String sku;
    private Integer price;
    private Image image;
    private String parentName;

}