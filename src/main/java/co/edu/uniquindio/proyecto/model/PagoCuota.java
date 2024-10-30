package co.edu.uniquindio.proyecto.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "PAGO_CUOTA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class PagoCuota implements Serializable {

    @Id
    @Column(name = "ID_PAGO_CUOTA", nullable = false)
    @EqualsAndHashCode.Include
    private int idPagoCuota;

    @ManyToOne
    @JoinColumn(name = "PRESTAMO_ID_PRESTAMO", nullable = false)
    private Prestamo prestamo;

    @Column(name = "NUMERO_CUOTA", length = 10, nullable = false)
    private int numeroCuota;

    @Column(name = "FECHA_PAGO", length = 20, nullable = false)
    private String fechaPago;

    @Column(name = "VALOR_PAGO", length = 20, nullable = false)
    private float valorPago;



}
