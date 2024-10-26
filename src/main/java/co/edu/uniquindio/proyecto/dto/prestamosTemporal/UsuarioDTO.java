package co.edu.uniquindio.proyecto.dto.prestamosTemporal;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO implements Serializable {

    private int idUsuario;
    private int nivelAccesoId;  // Suponiendo que solo necesitas el ID del nivel de acceso
    private String login;
    private String clave;  // Ten cuidado al transferir contraseñas
}