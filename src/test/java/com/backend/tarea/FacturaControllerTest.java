package com.backend.tarea;

import com.backend.tarea.controller.FacturaController;
import com.backend.tarea.model.Factura;
import com.backend.tarea.service.FacturaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class FacturaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FacturaService facturaService;

    @InjectMocks
    private FacturaController facturaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(facturaController).build(); // Usar standaloneSetup
    }

    @Test
    public void testObtenerFacturas() throws Exception {
        // Simular datos de prueba
        Factura factura1 = new Factura();
        factura1.setIdFactura(1L);
        factura1.setMetodoPago("Tarjeta");
        factura1.setMontoTotal(100.0);

        Factura factura2 = new Factura();
        factura2.setIdFactura(2L);
        factura2.setMetodoPago("Efectivo");
        factura2.setMontoTotal(50.0);

        when(facturaService.listarFacturas()).thenReturn(Arrays.asList(factura1, factura2));

        // Simular la petición HTTP y verificar la respuesta
        mockMvc.perform(get("/api/facturas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].metodoPago").value("Tarjeta"))
                .andExpect(jsonPath("$[1].metodoPago").value("Efectivo"));
    }
}
