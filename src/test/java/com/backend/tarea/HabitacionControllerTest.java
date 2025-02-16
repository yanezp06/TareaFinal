package com.backend.tarea;

import com.backend.tarea.controller.HabitacionController;
import com.backend.tarea.model.Habitacion;
import com.backend.tarea.service.HabitacionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HabitacionController.class)
public class HabitacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HabitacionService habitacionService;

    private Habitacion habitacion1;
    private Habitacion habitacion2;

    @BeforeEach
    public void setUp() {
        habitacion1 = new Habitacion("Suite", "Disponible", 150.0);
        habitacion2 = new Habitacion("Doble", "Ocupada", 100.0);
    }

    @Test
    public void testListarHabitaciones() throws Exception {
        when(habitacionService.listarHabitaciones()).thenReturn(Arrays.asList(habitacion1, habitacion2));

        mockMvc.perform(get("/api/habitaciones")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tipo").value("Suite"))
                .andExpect(jsonPath("$[1].tipo").value("Doble"));
    }

    @Test
    public void testGuardarHabitacion() throws Exception {
        when(habitacionService.guardarHabitacion(any(Habitacion.class))).thenReturn(habitacion1);

        String habitacionJson = """
                {
                    "tipo": "Suite",
                    "estado": "Disponible",
                    "precio": 150.0
                }
                """;

        mockMvc.perform(post("/api/habitaciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(habitacionJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipo").value("Suite"))
                .andExpect(jsonPath("$.estado").value("Disponible"))
                .andExpect(jsonPath("$.precio").value(150.0));
    }

    @Test
    public void testObtenerHabitacionPorId() throws Exception {
        when(habitacionService.obtenerHabitacionPorId(1L)).thenReturn(Optional.of(habitacion1));

        mockMvc.perform(get("/api/habitaciones/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipo").value("Suite"))
                .andExpect(jsonPath("$.estado").value("Disponible"));
    }

    @Test
    public void testEliminarHabitacion() throws Exception {
        mockMvc.perform(delete("/api/habitaciones/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(habitacionService, times(1)).eliminarHabitacion(1L);
    }
}
