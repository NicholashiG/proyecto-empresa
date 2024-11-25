package co.edu.uniquindio.proyecto.model;

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
@ToString(onlyExplicitlyIncluded = true)
public class NivelAcceso implements Serializable {

    @Id
    @Column(name = "ID_NIVEL", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private int idTipo;

    @ToString.Include
    @Column(name = "NOMBRE", length = 30, nullable = false)
    private String nombre;

    @Column(name = "DESCRIPCION", length = 110, nullable = false)
    private String descripcion;

}
