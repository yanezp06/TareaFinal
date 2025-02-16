
package com.backend.tarea.repository;

import com.backend.tarea.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    // Puedes añadir métodos personalizados si es necesario
}
