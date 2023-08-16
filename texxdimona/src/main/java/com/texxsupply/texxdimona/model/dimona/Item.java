package com.texxsupply.texxdimona.model.dimona;
import com.texxsupply.texxdimona.model.wordpress.LineItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Item {

    private String name;
    private Integer qty;
    private String sku;

    public Item(LineItem lineItem){
        if (lineItem.getSku()!=""){
            this.name = lineItem.getName()
                    .concat(", ")
                    .concat(lineItem.getMeta_data().get(0).getDisplay_key())
                    .concat(" ")
                    .concat(lineItem.getMeta_data().get(0).getDisplay_value())
                    .concat(", ")
                    .concat(lineItem.getMeta_data().get(1).getDisplay_value())
            ;
            this.qty = lineItem.getQuantity();
            this.sku = lineItem.getSku();
        } else {
            this.name = "Produto Não Dimona";
            this.qty = 1;
            this.sku = "";
        }
    }



}