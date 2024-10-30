package co.edu.uniquindio.proyecto.repositories;

import co.edu.uniquindio.proyecto.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudRepo extends JpaRepository<Solicitud, Integer> {

    // Encuentra solicitudes por el estado
    List<Solicitud> findByEstado(String estado);

    // Encuentra solicitudes por el empleado asociado
    List<Solicitud> findByEmpleado_IdEmpleado(int idEmpleado);

    // Encuentra solicitudes por el monto solicitado
    List<Solicitud> findByMontoSolicitado(float montoSolicitado);
}
