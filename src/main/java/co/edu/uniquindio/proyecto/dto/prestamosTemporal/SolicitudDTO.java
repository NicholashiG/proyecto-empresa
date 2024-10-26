package co.edu.uniquindio.proyecto.dto.prestamosTemporal;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDTO implements Serializable {

    private int idSolicitud;
    private String fecha;
    private int empleadoId;  // Suponiendo que solo necesitas el ID del empleado
    private float montoSolicitado;
    private String estado;
    private int periodoSolicitudId;  // Suponiendo que solo necesitas el ID del periodo de solicitud
}
