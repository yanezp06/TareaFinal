/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.tarea.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Reserva") // Mapea esta clase con la tabla "Reserva" en la base de datos
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autogenerado
    @Column(name = "id_reserva") // Mapea esta columna con "id_reserva" en la tabla
    private Long idReserva;

    @Column(name = "fecha_inicio", nullable = false) // Fecha de inicio de la reserva
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false) // Fecha de fin de la reserva
    private LocalDate fechaFin;

    @Column(name = "estado", nullable = false, length = 50) // Ejemplo: Creada, Confirmada, Cancelada
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false) // Relación con la tabla Usuario
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false) // Relación con la tabla Cliente
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_habitacion", nullable = false) // Relación con la tabla Habitacion
    private Habitacion habitacion;

    // Constructor vacío para JPA
    public Reserva() {
    }

    // Constructor con parámetros
    public Reserva(LocalDate fechaInicio, LocalDate fechaFin, String estado, Usuario usuario, Cliente cliente, Habitacion habitacion) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.usuario = usuario;
        this.cliente = cliente;
        this.habitacion = habitacion;
    }

    // Getters y Setters
    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    // Sobrescribir el método toString para imprimir información de la reserva
    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva=" + idReserva +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", estado='" + estado + '\'' +
                ", usuario=" + usuario +
                ", cliente=" + cliente +
                ", habitacion=" + habitacion +
                '}';
    }
}
