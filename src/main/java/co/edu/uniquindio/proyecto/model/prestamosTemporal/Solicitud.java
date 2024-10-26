package co.edu.uniquindio.proyecto.model.prestamosTemporal;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "SOLICITUD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Solicitud implements Serializable {

    @Id
    @Column(name = "ID_SOLICITUD", nullable = false)
    @EqualsAndHashCode.Include
    private int idSolicitud;

    @Column(name = "FECHA", length = 20, nullable = false)
    private String fecha;

    @ManyToOne
    @JoinColumn(name = "EMPLEADO_ID_EMPLEADO", nullable = false)
    private EmpleadoNico empleado;

    @Column(name = "MONTO_SOLICITADO", length = 20, nullable = false)
    private float montoSolicitado;

    @Column(name = "ESTADO", length = 50, nullable = false)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "PERIODO_SOLICITUD_ID_PERIODO_SOLICITUD", nullable = false)
    private PeriodoSolicitud periodoSolicitud;


}
