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
public class ShippingLine {

    public Integer id;
    public String methodTitle;
    public String methodId;
    public String instanceId;
    public String total;
    public String totalTax;
    public List<Object> taxes;
    public List<Object> metaData;

}