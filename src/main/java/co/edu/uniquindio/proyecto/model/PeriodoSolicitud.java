package co.edu.uniquindio.proyecto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "PERIODO_SOLICITUD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class PeriodoSolicitud implements Serializable {

    @Id
    @Column(name = "ID_PERIODO_SOLICITUD", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private int idPeriodoSolicitud;

    @Column(name = "MESES", length = 10, nullable = false)
    @ToString.Include
    private int meses;

    @Column(name = "TASA_INTERES", length = 10, nullable = false)
    private float tasaInteres;

}
