package co.edu.uniquindio.proyecto.repositories;


import co.edu.uniquindio.proyecto.model.PagoCuota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PagoCuotaRepo extends JpaRepository<PagoCuota, Integer> {

    // Encuentra pagos de cuotas por el ID del préstamo
   // List<PagoCuota> findByPrestamo_IdPrestamo(int idPrestamo);
    Optional<PagoCuota> findFirstByPrestamo_IdPrestamoOrderByNumeroCuotaDesc(int prestamoId);

    // Encuentra pagos de cuotas por el número de cuota
    List<PagoCuota> findByNumeroCuota(int numeroCuota);

}