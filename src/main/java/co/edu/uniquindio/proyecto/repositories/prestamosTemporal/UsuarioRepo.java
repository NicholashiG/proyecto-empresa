package co.edu.uniquindio.proyecto.repositories.prestamosTemporal;

import co.edu.uniquindio.proyecto.model.prestamosTemporal.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

    // Encuentra un usuario por su login
    Optional<Usuario> findByLogin(String login);

    // Verifica si existe un usuario con un login específico
    boolean existsByLogin(String login);
}
