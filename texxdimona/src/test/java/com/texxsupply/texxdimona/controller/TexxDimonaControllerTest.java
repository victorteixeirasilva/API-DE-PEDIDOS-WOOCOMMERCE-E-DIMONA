package com.texxsupply.texxdimona.controller;


import com.texxsupply.texxdimona.model.ResponseModel;
import com.texxsupply.texxdimona.service.TexxDimonaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.mockito.Mockito.*;

public class TexxDimonaControllerTest {

    @Mock
    private TexxDimonaService texxDimonaService;

    @InjectMocks
    private TexxDimonaController texxDimonaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProductWordpress_Success() {
        ResponseEntity expectedResult = ResponseEntity.ok().build();
        when(texxDimonaService.insertOrder()).thenReturn(expectedResult);

        ResponseEntity actualResult = texxDimonaController.getProductWordpress();

        verify(texxDimonaService, times(1)).insertOrder();
        verifyNoMoreInteractions(texxDimonaService);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetProductWordpress_Exception() {
        when(texxDimonaService.insertOrder()).thenThrow(new RuntimeException("Test Exception"));

        ResponseEntity<ResponseModel> expectedResponse = new ResponseEntity<>(
                new ResponseModel(500, "Não foi possível integrar os Pedidos da Texx Supply com a Dimona!"),
                HttpStatus.INTERNAL_SERVER_ERROR
        );

        ResponseEntity actualResult = texxDimonaController.getProductWordpress();

        verify(texxDimonaService, times(1)).insertOrder();
        verifyNoMoreInteractions(texxDimonaService);
        Assertions.assertEquals(expectedResponse, actualResult);
    }
}

