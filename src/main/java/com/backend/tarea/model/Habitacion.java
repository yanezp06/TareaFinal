package com.backend.tarea.model;
import jakarta.persistence.*;

@Entity
@Table(name = "Habitacion") // Mapea esta clase con la tabla "Habitacion" en la base de datos
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autogenerado
    @Column(name = "id_habitacion") // Mapea esta columna con "id_habitacion" en la tabla
    private Long idHabitacion;

    @Column(name = "tipo", nullable = false, length = 50) // Ejemplo: Simple, Doble, Suite
    private String tipo;

    @Column(name = "estado", nullable = false, length = 50) // Ejemplo: Disponible, Ocupada, Mantenimiento
    private String estado;

    @Column(name = "precio", nullable = false) // Precio de la habitación
    private Double precio;

    // Constructor vacío para JPA
    public Habitacion() {
    }

    // Constructor con parámetros
    public Habitacion(String tipo, String estado, Double precio) {
        this.tipo = tipo;
        this.estado = estado;
        this.precio = precio;
    }

    // Getters y Setters
    public Long getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(Long idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    // Sobrescribir el método toString para imprimir información de la habitación
    @Override
    public String toString() {
        return "Habitacion{" +
                "idHabitacion=" + idHabitacion +
                ", tipo='" + tipo + '\'' +
                ", estado='" + estado + '\'' +
                ", precio=" + precio +
                '}';
    }
}
