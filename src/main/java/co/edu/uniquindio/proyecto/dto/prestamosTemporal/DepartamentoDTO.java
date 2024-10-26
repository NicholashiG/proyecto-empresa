package co.edu.uniquindio.proyecto.dto.prestamosTemporal;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoDTO implements Serializable {

    private int idDepartamento;
    private String nombre;
}
