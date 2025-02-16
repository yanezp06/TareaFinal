package com.backend.tarea;

import com.backend.tarea.model.Habitacion;
import com.backend.tarea.repository.HabitacionRepository;
import com.backend.tarea.service.HabitacionService;
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

public class HabitacionServiceTest {

    @InjectMocks
    private HabitacionService habitacionService;

    @Mock
    private HabitacionRepository habitacionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarHabitacion() {
        Habitacion habitacion = new Habitacion();
        habitacion.setTipo("Doble");
        habitacion.setPrecio(100.0);
        when(habitacionRepository.save(habitacion)).thenReturn(habitacion);

        Habitacion resultado = habitacionService.guardarHabitacion(habitacion);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getTipo()).isEqualTo("Doble");
        assertThat(resultado.getPrecio()).isEqualTo(100.0);
        verify(habitacionRepository, times(1)).save(habitacion);
    }

    @Test
    public void testObtenerHabitacionPorId() {
        Habitacion habitacion = new Habitacion();
        habitacion.setIdHabitacion(1L);
        habitacion.setTipo("Suite");
        when(habitacionRepository.findById(1L)).thenReturn(Optional.of(habitacion));

        Optional<Habitacion> resultado = habitacionService.obtenerHabitacionPorId(1L);

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getTipo()).isEqualTo("Suite");
        verify(habitacionRepository, times(1)).findById(1L);
    }

    @Test
    public void testListarHabitaciones() {
        Habitacion habitacion1 = new Habitacion();
        habitacion1.setTipo("Doble");
        Habitacion habitacion2 = new Habitacion();
        habitacion2.setTipo("Individual");

        when(habitacionRepository.findAll()).thenReturn(Arrays.asList(habitacion1, habitacion2));

        List<Habitacion> resultado = habitacionService.listarHabitaciones();

        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getTipo()).isEqualTo("Doble");
        assertThat(resultado.get(1).getTipo()).isEqualTo("Individual");
        verify(habitacionRepository, times(1)).findAll();
    }

    @Test
    public void testEliminarHabitacion() {
        Long idHabitacion = 1L;

        doNothing().when(habitacionRepository).deleteById(idHabitacion);

        habitacionService.eliminarHabitacion(idHabitacion);

        verify(habitacionRepository, times(1)).deleteById(idHabitacion);
    }
}
