package co.edu.uniquindio.proyecto.model.prestamosTemporal;

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
@ToString
public class Sucursal implements Serializable {

    @Id
    @Column(name = "ID_SUCURSAL", nullable = false)
    @EqualsAndHashCode.Include
    private int idSucursal;

    @Column(name = "NOMBRE", length = 30, nullable = false)
    private String nombre;

    @Column(name = "DIRECCION", length = 110, nullable = false)
    private String direccion;

    @OneToOne
    @JoinColumn(name = "MUNICIPIO_ID_MUNICIPIO", nullable = false)
    private Municipio municipio;


}


