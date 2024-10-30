package co.edu.uniquindio.proyecto.dto;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MunicipioDTO implements Serializable {

    private int idMunicipio;
    private String nombre;
    private int departamentoId;  // Suponiendo que solo necesitas el ID del departamento
}