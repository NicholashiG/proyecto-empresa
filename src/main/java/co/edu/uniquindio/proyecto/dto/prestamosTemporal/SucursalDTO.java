package co.edu.uniquindio.proyecto.dto.prestamosTemporal;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SucursalDTO implements Serializable {

    private int idSucursal;
    private String nombre;
    private String direccion;
    private int municipioId;  // Suponiendo que solo necesitas el ID del municipio
}