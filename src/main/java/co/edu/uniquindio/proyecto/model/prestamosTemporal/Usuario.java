package co.edu.uniquindio.proyecto.model.prestamosTemporal;

import co.edu.uniquindio.proyecto.model.TipoHotel;
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
@ToString
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO", nullable = false)
    @EqualsAndHashCode.Include
    private int idUsuario;

    @ManyToOne
    @JoinColumn(name = "NIVEL_ACCESO_ID_NIVEL", nullable = false)
    private NivelAcceso nivelAcceso;

    @Column(name = "LOGIN", nullable = false)
    private String login;

    @Column(name = "CLAVE", nullable = false)
    private String clave;

}
