package co.edu.uniquindio.proyecto.model.prestamosTemporal;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "EMPLEADO_NICO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class EmpleadoNico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EMPLEADO", nullable = false)
    @EqualsAndHashCode.Include
    private int idEmpleado;

    @OneToOne
    @JoinColumn(name = "USUARIO_ID_USUARIO", nullable = false)
    private Usuario usuario;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "APELLIDO", nullable = false)
    private String apellido;

    @OneToOne
    @JoinColumn(name = "TIPO_EMPLEADO_ID_TIPO_EMPLEADO", nullable = false)
    private TipoEmpleado tipoEmpleado;

    @ManyToOne
    @JoinColumn(name = "SUCURSAL_ID_SUCURSAL", nullable = false)
    private Sucursal sucursal;

}
