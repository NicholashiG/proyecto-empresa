package co.edu.uniquindio.proyecto.repositories;

import co.edu.uniquindio.proyecto.model.TipoEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoEmpleadoRepo extends JpaRepository<TipoEmpleado, Integer> {

    // Encuentra un tipo de empleado por su nombre
    Optional<TipoEmpleado> findByNombre(String nombre);

    // Verifica si existe un tipo de empleado con un nombre específico
    boolean existsByNombre(String nombre);
}
