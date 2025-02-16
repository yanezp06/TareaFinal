package com.backend.tarea;

import com.backend.tarea.model.Reserva;
import com.backend.tarea.repository.ReservaRepository;
import com.backend.tarea.service.ReservaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ReservaServiceTest {

    @InjectMocks
    private ReservaService reservaService;

    @Mock
    private ReservaRepository reservaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarReserva() {
        Reserva reserva = new Reserva();
        reserva.setEstado("Confirmada");
        reserva.setFechaInicio(LocalDate.of(2025, 1, 1));
        reserva.setFechaFin(LocalDate.of(2025, 1, 5));
        when(reservaRepository.save(reserva)).thenReturn(reserva);

        Reserva resultado = reservaService.guardarReserva(reserva);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getEstado()).isEqualTo("Confirmada");
        assertThat(resultado.getFechaInicio()).isEqualTo(LocalDate.of(2025, 1, 1));
        assertThat(resultado.getFechaFin()).isEqualTo(LocalDate.of(2025, 1, 5));
        verify(reservaRepository, times(1)).save(reserva);
    }

    @Test
    public void testObtenerReservaPorId() {
        Reserva reserva = new Reserva();
        reserva.setIdReserva(1L);
        reserva.setEstado("Cancelada");
        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reserva));

        Optional<Reserva> resultado = reservaService.obtenerReservaPorId(1L);

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getEstado()).isEqualTo("Cancelada");
        verify(reservaRepository, times(1)).findById(1L);
    }

    @Test
    public void testListarReservas() {
        Reserva reserva1 = new Reserva();
        reserva1.setEstado("Confirmada");
        Reserva reserva2 = new Reserva();
        reserva2.setEstado("Pendiente");

        when(reservaRepository.findAll()).thenReturn(Arrays.asList(reserva1, reserva2));

        List<Reserva> resultado = reservaService.listarReservas();

        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getEstado()).isEqualTo("Confirmada");
        assertThat(resultado.get(1).getEstado()).isEqualTo("Pendiente");
        verify(reservaRepository, times(1)).findAll();
    }

    @Test
    public void testEliminarReserva() {
        Long idReserva = 1L;

        doNothing().when(reservaRepository).deleteById(idReserva);

        reservaService.eliminarReserva(idReserva);

        verify(reservaRepository, times(1)).deleteById(idReserva);
    }
}