package com.backend.tarea.controller;

import com.backend.tarea.model.Habitacion;
import com.backend.tarea.service.HabitacionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/habitaciones") // Ruta base para Habitacion
@CrossOrigin(origins = "*") 
public class HabitacionController {

    private final HabitacionService habitacionService;

    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @GetMapping
    public List<Habitacion> listarHabitaciones() {
        return habitacionService.listarHabitaciones();
    }

    @PostMapping
    public Habitacion guardarHabitacion(@RequestBody Habitacion habitacion) {
        return habitacionService.guardarHabitacion(habitacion);
    }

    @GetMapping("/{id}")
    public Optional<Habitacion> obtenerHabitacionPorId(@PathVariable Long id) {
        return habitacionService.obtenerHabitacionPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarHabitacion(@PathVariable Long id) {
        habitacionService.eliminarHabitacion(id);
    }
}