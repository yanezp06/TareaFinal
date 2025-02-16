
package com.backend.tarea;

import com.backend.tarea.model.Factura;
import com.backend.tarea.repository.FacturaRepository;
import com.backend.tarea.service.FacturaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class FacturaServiceTest {

    @InjectMocks
    private FacturaService facturaService;

    @Mock
    private FacturaRepository facturaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarFactura() {
        Factura factura = new Factura();
        factura.setMetodoPago("Tarjeta de crédito");
        when(facturaRepository.save(factura)).thenReturn(factura);

        Factura resultado = facturaService.guardarFactura(factura);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getMetodoPago()).isEqualTo("Tarjeta de crédito");
        verify(facturaRepository, times(1)).save(factura);
    }

    @Test
    public void testObtenerFacturaPorId() {
        Factura factura = new Factura();
        factura.setIdFactura(1L);
        factura.setMetodoPago("Tarjeta de crédito");
        when(facturaRepository.findById(1L)).thenReturn(Optional.of(factura));

        Optional<Factura> resultado = facturaService.obtenerFacturaPorId(1L);

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getMetodoPago()).isEqualTo("Tarjeta de crédito");
        verify(facturaRepository, times(1)).findById(1L);
    }

    @Test
    public void testListarFacturas() {
        Factura factura1 = new Factura();
        factura1.setMetodoPago("Tarjeta de crédito");
        Factura factura2 = new Factura();
        factura2.setMetodoPago("Efectivo");

        when(facturaRepository.findAll()).thenReturn(Arrays.asList(factura1, factura2));

        List<Factura> resultado = facturaService.listarFacturas();

        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getMetodoPago()).isEqualTo("Tarjeta de crédito");
        assertThat(resultado.get(1).getMetodoPago()).isEqualTo("Efectivo");
        verify(facturaRepository, times(1)).findAll();
    }

    @Test
    public void testEliminarFactura() {
        Long idFactura = 1L;

        doNothing().when(facturaRepository).deleteById(idFactura);

        facturaService.eliminarFactura(idFactura);

        verify(facturaRepository, times(1)).deleteById(idFactura);
    }
}
