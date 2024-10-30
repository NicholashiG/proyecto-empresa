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
@ToString
public class Municipio implements Serializable {

    @Id
    @Column(name = "ID_MUNICIPIO", nullable = false)
    @EqualsAndHashCode.Include
    private int idMunicipio;

    @Column(name = "NOMBRE", length = 30, nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "DEPARTAMENTO_ID_DEPARTAMENTO", nullable = false)
    private Departamento departamento;

}
