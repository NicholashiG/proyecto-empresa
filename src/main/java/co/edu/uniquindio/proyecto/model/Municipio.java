package co.edu.uniquindio.proyecto.model;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "MUNICIPIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Municipio implements Serializable {

    @Id
    @Column(name = "ID_MUNICIPIO", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private int idMunicipio;

    @ToString.Include
    @Column(name = "NOMBRE", length = 30, nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "DEPARTAMENTO_ID_DEPARTAMENTO", nullable = false)
    private Departamento departamento;

}
