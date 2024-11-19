package co.edu.uniquindio.proyecto.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "estado_empleado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class EstadoEmpleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstadoEmpleado;

    @Column(name = "id_empleado", nullable = false)
    private Integer idEmpleado;

    @Column(nullable = false, length = 50)
    private String estado;

    @Column(nullable = false)
    private Timestamp fechaActualizacion;

    // Getters y Setters
}
