package co.edu.uniquindio.proyecto.repositories.prestamosTemporal;

import co.edu.uniquindio.proyecto.model.prestamosTemporal.PeriodoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeriodoSolicitudRepo extends JpaRepository<PeriodoSolicitud, Integer> {

    // Encuentra un periodo de solicitud por la tasa de interés
    Optional<PeriodoSolicitud> findByTasaInteres(float tasaInteres);

    // Encuentra un periodo de solicitud por el número de meses
    Optional<PeriodoSolicitud> findByMeses(int meses);
}
