package com.texxsupply.texxdimona.service;


import com.texxsupply.texxdimona.model.ResponseModel;
import com.texxsupply.texxdimona.model.dimona.OrderDimona;
import com.texxsupply.texxdimona.model.wordpress.*;
import com.texxsupply.texxdimona.service.DimonaService;
import com.texxsupply.texxdimona.service.WordpressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TexxDimonaServiceTest {

    @Mock
    private WordpressService wordpressService;

    @Mock
    private DimonaService dimonaService;

    @InjectMocks
    private TexxDimonaService texxDimonaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsertOrder_Success() {
        List<OrderWorpress> orders = new ArrayList<>();
        OrderWorpress order1 = new OrderWorpress();
        order1.setStatus("processing");
        order1.setId(4109);
        order1.setCreated_via("checkout");


        Billing billing = new Billing();
        billing.setFirst_name("Victor");
        billing.setLast_name("Arthur");
        billing.setCpf("52453357894");
        billing.setEmail("texx@gmail.com");
        billing.setCellphone("11991502974");
        order1.setBilling(billing);

        Shipping shipping = new Shipping();
        shipping.setFirst_name("Victor");
        shipping.setLast_name("Arthur");
        shipping.setAddress_1("Rua João");
        shipping.setNumber("374");
        shipping.setAddress_2("Casa 2");
        shipping.setCity("São Paulo");
        shipping.setState("SP");
        shipping.setPostcode("04917130");
        shipping.setNeighborhood("Jardim Souza");
        shipping.setCountry("BR");
        order1.setShipping(shipping);


        List<LineItem> lineItems = new ArrayList<>();
        LineItem lineItem1 = new LineItem();
        lineItem1.setSku("SKU123");
        lineItem1.setName("aaaaaa");

        List<MetaDatum__1> metaDatum__1s = new ArrayList<>();
        MetaDatum__1 metaDatum__1 = new MetaDatum__1();
        metaDatum__1.setDisplay_key("Tamanho");
        metaDatum__1.setDisplay_value("G");
        metaDatum__1s.add(metaDatum__1);

        MetaDatum__1 metaDatum__11 = new MetaDatum__1();
        metaDatum__11.setDisplay_value("Modelo 01");
        metaDatum__1s.add(metaDatum__11);

        lineItem1.setMeta_data(metaDatum__1s);
        lineItems.add(lineItem1);
        order1.setLine_items(lineItems);
        orders.add(order1);

        when(wordpressService.getOrders()).thenReturn(orders);
        when(dimonaService.insertOrder(any(OrderDimona.class))).thenReturn(ResponseEntity.ok(orders));

        ResponseEntity expectedResponse = ResponseEntity.ok(orders);
        ResponseEntity actualResponse = texxDimonaService.insertOrder();

        verify(wordpressService, times(1)).getOrders();
        verify(dimonaService, times(1)).insertOrder(any(OrderDimona.class));
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testInsertOrder_SuccessOnshopAndDimona() {
        List<OrderWorpress> orders = new ArrayList<>();
        OrderWorpress order1 = new OrderWorpress();
        order1.setStatus("processing");
        order1.setId(4109);
        order1.setCreated_via("checkout");

        Billing billing = new Billing();
        billing.setFirst_name("Victor");
        billing.setLast_name("Arthur");
        billing.setCpf("52453357894");
        billing.setEmail("texx@gmail.com");
        billing.setCellphone("11991502974");
        order1.setBilling(billing);

        Shipping shipping = new Shipping();
        shipping.setFirst_name("Victor");
        shipping.setLast_name("Arthur");
        shipping.setAddress_1("Rua João");
        shipping.setNumber("374");
        shipping.setAddress_2("Casa 2");
        shipping.setCity("São Paulo");
        shipping.setState("SP");
        shipping.setPostcode("04917130");
        shipping.setNeighborhood("Jardim Souza");
        shipping.setCountry("BR");
        order1.setShipping(shipping);

        //Item 1
        List<LineItem> lineItems = new ArrayList<>();
        LineItem lineItem1 = new LineItem();
        lineItem1.setSku("SKU123");
        lineItem1.setName("aaaaaa");

        List<MetaDatum__1> metaDatum__1s = new ArrayList<>();
        MetaDatum__1 metaDatum__1 = new MetaDatum__1();
        metaDatum__1.setDisplay_key("Tamanho");
        metaDatum__1.setDisplay_value("G");
        metaDatum__1s.add(metaDatum__1);

        MetaDatum__1 metaDatum__11 = new MetaDatum__1();
        metaDatum__11.setDisplay_value("Modelo 01");
        metaDatum__1s.add(metaDatum__11);

        lineItem1.setMeta_data(metaDatum__1s);
        lineItems.add(lineItem1);

        //Item 2
        LineItem lineItem10 = new LineItem();
        lineItem10.setSku("");
        lineItem10.setName("aaaaaa");

        List<MetaDatum__1> metaDatum__10s = new ArrayList<>();
        MetaDatum__1 metaDatum__10 = new MetaDatum__1();
        metaDatum__10.setDisplay_key("Tamanho");
        metaDatum__10.setDisplay_value("G");
        metaDatum__10s.add(metaDatum__10);

        MetaDatum__1 metaDatum__110 = new MetaDatum__1();
        metaDatum__110.setDisplay_value("Modelo 01");
        metaDatum__10s.add(metaDatum__110);

        lineItem10.setMeta_data(metaDatum__10s);
        lineItems.add(lineItem10);

        order1.setLine_items(lineItems);
        orders.add(order1);

        when(wordpressService.getOrders()).thenReturn(orders);
        orders.get(0).getLine_items().remove(lineItem10);
        when(dimonaService.insertOrder(any(OrderDimona.class))).thenReturn(ResponseEntity.ok(orders));

        ResponseEntity expectedResponse = ResponseEntity.ok(orders);
        ResponseEntity actualResponse = texxDimonaService.insertOrder();

        verify(wordpressService, times(1)).getOrders();
        verify(dimonaService, times(1)).insertOrder(any(OrderDimona.class));
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testInsertOrder_NoMatchingOrders() {
        List<OrderWorpress> orders = new ArrayList<>();
        OrderWorpress order1 = new OrderWorpress();
        order1.setStatus("sdasdaads");
        order1.setId(4109);

        Billing billing = new Billing();
        billing.setFirst_name("Victor");
        billing.setLast_name("Arthur");
        billing.setCpf("52453357894");
        billing.setEmail("texx@gmail.com");
        billing.setCellphone("11991502974");
        order1.setBilling(billing);

        Shipping shipping = new Shipping();
        shipping.setFirst_name("Victor");
        shipping.setLast_name("Arthur");
        shipping.setAddress_1("Rua João");
        shipping.setNumber("374");
        shipping.setAddress_2("Casa 2");
        shipping.setCity("São Paulo");
        shipping.setState("SP");
        shipping.setPostcode("04917130");
        shipping.setNeighborhood("Jardim Souza");
        shipping.setCountry("BR");
        order1.setShipping(shipping);


        List<LineItem> lineItems = new ArrayList<>();
        LineItem lineItem1 = new LineItem();
        lineItem1.setSku("");
        lineItem1.setName("aaaaaa");

        List<MetaDatum__1> metaDatum__1s = new ArrayList<>();
        MetaDatum__1 metaDatum__1 = new MetaDatum__1();
        metaDatum__1.setDisplay_key("Tamanho");
        metaDatum__1.setDisplay_value("G");
        metaDatum__1s.add(metaDatum__1);

        MetaDatum__1 metaDatum__11 = new MetaDatum__1();
        metaDatum__11.setDisplay_value("Modelo 01");
        metaDatum__1s.add(metaDatum__11);

        lineItem1.setMeta_data(metaDatum__1s);
        lineItems.add(lineItem1);
        order1.setLine_items(lineItems);
        orders.add(order1);

        when(wordpressService.getOrders()).thenReturn(orders);

        ResponseEntity<ResponseModel> expectedResponse = new ResponseEntity<>(
                new ResponseModel(404, "Não foi encontrado nenhum pedido da Texx que se enquadrase, nos pedidos Dimona para ser integrado!"),
                HttpStatus.NOT_FOUND
        );

        ResponseEntity actualResponse = texxDimonaService.insertOrder();

        verify(wordpressService, times(1)).getOrders();
        verifyNoInteractions(dimonaService);
        assertEquals(expectedResponse, actualResponse);
    }
}
