package com.backend.tarea.model;
import jakarta.persistence.*;

@Entity
@Table(name = "Cliente") // Mapea esta clase con la tabla "Cliente" en la base de datos
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autogenerado
    @Column(name = "id_cliente") // Mapea esta columna con "id_cliente" en la tabla
    private Long idCliente;

    @Column(name = "nombre", nullable = false, length = 255) // Campo obligatorio
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 255) // Campo obligatorio
    private String apellido;

    @Column(name = "documento", nullable = false, unique = true, length = 50) // Campo único
    private String documento;

    @Column(name = "telefono", length = 15) // Campo opcional
    private String telefono;

    @Column(name = "email", nullable = false, unique = true, length = 255) // Campo único
    private String email;

    // Constructor vacío para JPA
    public Cliente() {
    }

    // Constructor con parámetros
    public Cliente(String nombre, String apellido, String documento, String telefono, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.telefono = telefono;
        this.email = email;
    }

    // Getters y Setters
    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Sobrescribir el método toString para imprimir información del cliente
    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", documento='" + documento + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
