package co.edu.uniquindio.proyecto.model.prestamosTemporal;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "PRESTAMO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Prestamo implements Serializable {

    @Id
    @Column(name = "ID_PRESTAMO", nullable = false)
    @EqualsAndHashCode.Include
    private int idPrestamo;

    @OneToOne
    @JoinColumn(name = "SOLICITUD_ID_SOLICITUD", nullable = false)
    private Solicitud solicitud;

    @Column(name = "FECHA_DESEMBOLSO", length = 20, nullable = false)
    private String fechaDesembolso;

    @Column(name = "SALDO", length = 20, nullable = false)
    private float saldo;

}
