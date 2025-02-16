package com.backend.tarea.model;
import jakarta.persistence.*;

@Entity
@Table(name = "Usuario") // Mapea esta clase con la tabla "Usuario" en la base de datos
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autogenerado
    @Column(name = "id_usuario") // Mapea esta columna con "id_usuario" en la tabla
    private Long idUsuario;

    @Column(name = "nombre", nullable = false, length = 255) // Campo obligatorio
    private String nombre;

    @Column(name = "rol", nullable = false, length = 50) // Ejemplo: Administrador, Recepcionista, Contador
    private String rol;

    @Column(name = "email", nullable = false, unique = true, length = 255) // Campo único
    private String email;

    @Column(name = "contraseña", nullable = false, length = 255) // Campo obligatorio
    private String contraseña;

    // Constructor vacío para JPA
    public Usuario() {
    }

    // Constructor con parámetros
    public Usuario(String nombre, String rol, String email, String contraseña) {
        this.nombre = nombre;
        this.rol = rol;
        this.email = email;
        this.contraseña = contraseña;
    }

    // Getters y Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    // Sobrescribir el método toString para imprimir información del usuario
    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", rol='" + rol + '\'' +
                ", email='" + email + '\'' +
                ", contraseña='" + contraseña + '\'' +
                '}';
    }
}
