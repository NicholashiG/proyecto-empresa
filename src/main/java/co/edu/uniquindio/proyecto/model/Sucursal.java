package co.edu.uniquindio.proyecto.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "SUCURSAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Sucursal implements Serializable {

    @Id
    @Column(name = "ID_SUCURSAL", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private int idSucursal;

    @Column(name = "NOMBRE", length = 30, nullable = false)
    @ToString.Include
    private String nombre;

    @Column(name = "DIRECCION", length = 110, nullable = false)
    private String direccion;

    @OneToOne
    @ToString.Include
    @JoinColumn(name = "MUNICIPIO_ID_MUNICIPIO", nullable = false)
    private Municipio municipio;


}


