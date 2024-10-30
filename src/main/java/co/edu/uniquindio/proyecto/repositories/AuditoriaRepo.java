package co.edu.uniquindio.proyecto.repositories;

import co.edu.uniquindio.proyecto.model.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditoriaRepo extends JpaRepository<Auditoria, Integer> {
    // Aquí puedes añadir métodos de consulta personalizados si los necesitas

    List<Auditoria> findByAccion(String accion);
    List<Auditoria> findByFecha(String fecha);


}
