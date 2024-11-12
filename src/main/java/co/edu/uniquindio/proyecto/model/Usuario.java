package co.edu.uniquindio.proyecto.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "USUARIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private int idUsuario;

    @ManyToOne
    @JoinColumn(name = "NIVEL_ACCESO_ID_NIVEL", nullable = false)
    private NivelAcceso nivelAcceso;

    @Column(name = "LOGIN", nullable = false)
    @ToString.Include
    private String login;

    @Column(name = "CLAVE", nullable = false)
    @ToString.Include
    private String clave;

}
