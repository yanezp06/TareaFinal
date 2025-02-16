
package com.backend.tarea.repository;

import com.backend.tarea.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    // Puedes añadir métodos personalizados si es necesario
}
