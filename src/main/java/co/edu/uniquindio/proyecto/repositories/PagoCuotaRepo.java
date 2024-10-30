package co.edu.uniquindio.proyecto.repositories;


import co.edu.uniquindio.proyecto.model.PagoCuota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoCuotaRepo extends JpaRepository<PagoCuota, Integer> {

    // Encuentra pagos de cuotas por el ID del préstamo
    List<PagoCuota> findByPrestamo_IdPrestamo(int idPrestamo);

    // Encuentra pagos de cuotas por el número de cuota
    List<PagoCuota> findByNumeroCuota(int numeroCuota);
}