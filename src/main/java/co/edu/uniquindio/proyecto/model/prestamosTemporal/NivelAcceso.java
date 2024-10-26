package co.edu.uniquindio.proyecto.model.prestamosTemporal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "NIVEL_ACCESO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class NivelAcceso implements Serializable {

    @Id
    @Column(name = "ID_NIVEL", nullable = false)
    @EqualsAndHashCode.Include
    private int idTipo;

    @Column(name = "NOMBRE", length = 30, nullable = false)
    private String nombre;

    @Column(name = "DESCRIPCION", length = 110, nullable = false)
    private String descripcion;

}
