package co.edu.uniquindio.proyecto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "DEPARTAMENTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Departamento implements Serializable {

    @Id
    @Column(name = "ID_DEPARTAMENTO", nullable = false)
    @EqualsAndHashCode.Include
    private int idDepartamento;

    @Column(name = "NOMBRE", length = 30, nullable = false)
    private String nombre;

}
