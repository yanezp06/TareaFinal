package com.backend.tarea;

import com.backend.tarea.controller.ReservaController;
import com.backend.tarea.model.Reserva;
import com.backend.tarea.model.Usuario;
import com.backend.tarea.model.Cliente;
import com.backend.tarea.model.Habitacion;
import com.backend.tarea.service.ReservaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReservaController.class)
public class ReservaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservaService reservaService;

    private Reserva reserva1;
    private Reserva reserva2;

    @BeforeEach
    public void setUp() {
        Usuario usuario = new Usuario("Juan", "juan@example.com", "ADMIN", "password123");
        Cliente cliente = new Cliente("Carlos", "Perez", "12345678", "carlos@example.com", "555-1234");
        Habitacion habitacion = new Habitacion("Suite", "Disponible", 100.0);

        reserva1 = new Reserva(LocalDate.of(2025, 1, 10), LocalDate.of(2025, 1, 15), "CONFIRMADA", usuario, cliente, habitacion);
        reserva2 = new Reserva(LocalDate.of(2025, 2, 5), LocalDate.of(2025, 2, 10), "CANCELADA", usuario, cliente, habitacion);
    }

    @Test
    public void testListarReservas() throws Exception {
        when(reservaService.listarReservas()).thenReturn(Arrays.asList(reserva1, reserva2));

        mockMvc.perform(get("/api/reservas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado").value("CONFIRMADA"))
                .andExpect(jsonPath("$[1].estado").value("CANCELADA"));
    }

    @Test
    public void testGuardarReserva() throws Exception {
        when(reservaService.guardarReserva(any(Reserva.class))).thenReturn(reserva1);

        String reservaJson = """
                {
                    "fechaInicio": "2025-01-10",
                    "fechaFin": "2025-01-15",
                    "estado": "CONFIRMADA",
                    "usuario": {
                        "nombre": "Juan",
                        "email": "juan@example.com",
                        "rol": "ADMIN",
                        "password": "password123"
                    },
                    "cliente": {
                        "nombre": "Carlos",
                        "apellido": "Perez",
                        "documento": "12345678",
                        "email": "carlos@example.com",
                        "telefono": "555-1234"
                    },
                    "habitacion": {
                        "tipo": "Suite",
                        "estado": "Disponible",
                        "precio": 100.0
                    }
                }
                """;

        mockMvc.perform(post("/api/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(reservaJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("CONFIRMADA"));
    }

    @Test
    public void testObtenerReservaPorId() throws Exception {
        when(reservaService.obtenerReservaPorId(1L)).thenReturn(Optional.of(reserva1));

        mockMvc.perform(get("/api/reservas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("CONFIRMADA"));
    }

    @Test
    public void testEliminarReserva() throws Exception {
        mockMvc.perform(delete("/api/reservas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(reservaService, times(1)).eliminarReserva(1L);
    }
}
