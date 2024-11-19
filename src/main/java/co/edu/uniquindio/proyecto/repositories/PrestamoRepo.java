package co.edu.uniquindio.proyecto.repositories;

import co.edu.uniquindio.proyecto.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrestamoRepo extends JpaRepository<Prestamo, Integer> {

    Optional<Prestamo> findPrestamoByIdPrestamo(int idPrestamo);
    // Encuentra un préstamo por la solicitud asociada
    Optional<Prestamo> findBySolicitud_IdSolicitud(int idSolicitud);

    // Encuentra un préstamo por su saldo
    Optional<Prestamo> findBySaldo(float saldo);
}
