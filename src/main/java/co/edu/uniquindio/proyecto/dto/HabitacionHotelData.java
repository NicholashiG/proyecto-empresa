package co.edu.uniquindio.proyecto.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HabitacionHotelData {
    private String id_habitacion;
    private String tipo;
    private String capacidad;
    private String nivel;
    private Double precio_noche;


}
