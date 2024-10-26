package co.edu.uniquindio.proyecto.dto.prestamosTemporal;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditoriaDTO implements Serializable {

    private int idAuditoria;
    private String fecha;
    private String hora;
    private String accion;  // Usar 'accion' en minúscula para cumplir con las convenciones de nomenclatura
    private int usuarioId;  // Suponiendo que solo necesitas el ID del usuario en el DTO

}