package com.texxsupply.texxdimona.model.wordpress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * @Description: Classe responsável por ser o objeto de montagem do JSON..
 *
 * @author <a href="https://www.linkedin.com/in/victor-teixeira-354a131a3/">Victor Teixeira Silva</a>
 *
 * @version 1.0
 *
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class OrderWorpress {

    private Integer id;
    private Integer parent_id;
    private String status;
    private String currency;
    private String version;
    private Boolean prices_include_tax;
    private String date_created;
    private String date_modified;
    private String discount_total;
    private String discount_tax;
    private String shipping_total;
    private String shipping_tax;
    private String cart_tax;
    private String total;
    private String total_tax;
    private Integer customer_id;
    private String order_key;
    private Billing billing;
    private Shipping shipping;
    private String payment_method;
    private String payment_method_title;
    private String transaction_id;
    private String customer_ip_address;
    private String customer_user_agent;
    private String created_via;
    private String customer_note;
    private Object date_completed;
    private String date_paid;
    private String cart_hash;
    private String number;
    private List<MetaDatum> metaData;
    private List<LineItem> line_items;
    private List<Object> taxLines;
    private List<ShippingLine> shippingLines;
    private List<Object> feeLines;
    private List<Object> couponLines;
    private List<Object> refunds;
    private String paymentUrl;
    private Boolean isEditable;
    private Boolean needsPayment;
    private Boolean needsProcessing;
    private String dateCreatedGmt;
    private String dateModifiedGmt;
    private Object dateCompletedGmt;
    private String datePaidGmt;
    private String correiosTrackingCode;
    private String currencySymbol;
    private Links links;

}