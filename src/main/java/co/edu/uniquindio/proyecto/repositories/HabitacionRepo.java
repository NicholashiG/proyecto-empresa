package co.edu.uniquindio.proyecto.repositories;

import co.edu.uniquindio.proyecto.model.Habitacion;
import co.edu.uniquindio.proyecto.model.ReservaHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionRepo extends JpaRepository<Habitacion, Integer> {
}
