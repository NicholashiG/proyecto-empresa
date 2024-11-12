package co.edu.uniquindio.proyecto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "TIPO_EMPLEADO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class TipoEmpleado implements Serializable {

    @Id
    @Column(name = "ID_TIPO_EMPLEADO", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private int idTipoEmpleado;

    @Column(name = "NOMBRE", length = 30, nullable = false)
    @ToString.Include
    private String nombre;

    @Column(name = "PRESTAMO_MAXIMO", length = 30, nullable = false)
    private float prestamoMaximo;

    @Column(name = "SALARIO_MENSUAL", length = 30, nullable = false)
    private float salarioMensual;

}
