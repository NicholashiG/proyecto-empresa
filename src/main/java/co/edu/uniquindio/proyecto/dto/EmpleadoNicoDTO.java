package co.edu.uniquindio.proyecto.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoNicoDTO implements Serializable {

    private int idEmpleado;
    private int usuarioId;  // Suponiendo que solo necesitas el ID del usuario
    private String nombre;
    private String apellido;
    private int tipoEmpleadoId;  // Suponiendo que solo necesitas el ID del tipo de empleado
    private int sucursalId;  // Suponiendo que solo necesitas el ID de la sucursal
}
