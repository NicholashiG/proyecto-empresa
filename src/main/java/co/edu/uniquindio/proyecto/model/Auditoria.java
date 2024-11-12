package co.edu.uniquindio.proyecto.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "AUDITORIA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Auditoria implements Serializable {

    @Id
    @Column(name = "ID_AUDITORIA", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private int idAuditoria;

    @Column(name = "FECHA", length = 30, nullable = false)
    private String fecha;

    @Column(name = "HORA", length = 30, nullable = false)
    private String hora;

    @ToString.Include
    @Column(name = "ACCION", length = 110, nullable = false)
    private String accion;

    @ManyToOne
    @JoinColumn(name = "USUARIO_ID_USUARIO", nullable = false)
    private Usuario usuario;

}
