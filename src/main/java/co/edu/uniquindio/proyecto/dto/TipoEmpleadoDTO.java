package co.edu.uniquindio.proyecto.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoEmpleadoDTO implements Serializable {

    private int idTipoEmpleado;
    private String nombre;
    private float prestamoMaximo;
    private float salarioMensual;
}