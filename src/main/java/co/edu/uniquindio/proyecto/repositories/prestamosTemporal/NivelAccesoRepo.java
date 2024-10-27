package co.edu.uniquindio.proyecto.repositories.prestamosTemporal;

import co.edu.uniquindio.proyecto.model.prestamosTemporal.NivelAcceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NivelAccesoRepo extends JpaRepository<NivelAcceso, Integer> {

    // Encuentra un nivel de acceso por su nombre
    Optional<NivelAcceso> findByNombre(String nombre);

    // Verifica si existe un nivel de acceso con un nombre específico
    boolean existsByNombre(String nombre);
}