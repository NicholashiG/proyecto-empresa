package co.edu.uniquindio.proyecto.repositories;

import co.edu.uniquindio.proyecto.model.NivelAcceso;
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