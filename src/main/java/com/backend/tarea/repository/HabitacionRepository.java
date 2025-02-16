package com.backend.tarea.repository;
import com.backend.tarea.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    // Puedes añadir métodos personalizados si es necesario
}
