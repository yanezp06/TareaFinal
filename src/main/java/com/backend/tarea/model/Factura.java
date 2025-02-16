package com.backend.tarea.model;
import jakarta.persistence.*;

@Entity
@Table(name = "Factura") // Mapea esta clase con la tabla "Factura" en la base de datos
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autogenerado
    @Column(name = "id_factura") // Mapea esta columna con "id_factura" en la tabla
    private Long idFactura;

    @Column(name = "monto_total", nullable = false) // Monto total de la factura
    private Double montoTotal;

    @Column(name = "metodo_pago", nullable = false, length = 50) // Ejemplo: Efectivo, Tarjeta
    private String metodoPago;

    @OneToOne
    @JoinColumn(name = "id_reserva", nullable = false, unique = true) // Relación con la tabla Reserva
    private Reserva reserva;

    // Constructor vacío para JPA
    public Factura() {
    }

    // Constructor con parámetros
    public Factura(Double montoTotal, String metodoPago, Reserva reserva) {
        this.montoTotal = montoTotal;
        this.metodoPago = metodoPago;
        this.reserva = reserva;
    }

    // Getters y Setters
    public Long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    // Sobrescribir el método toString para imprimir información de la factura
    @Override
    public String toString() {
        return "Factura{" +
                "idFactura=" + idFactura +
                ", montoTotal=" + montoTotal +
                ", metodoPago='" + metodoPago + '\'' +
                ", reserva=" + reserva +
                '}';
    }
}
