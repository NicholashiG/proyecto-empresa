package co.edu.uniquindio.proyecto.repositories;

import co.edu.uniquindio.proyecto.model.EstadoEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoEmpleadoRepo extends JpaRepository<EstadoEmpleado, Long> {
    Optional<EstadoEmpleado> findByIdEmpleado(Integer idEmpleado);

}
