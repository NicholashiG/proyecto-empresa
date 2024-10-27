package co.edu.uniquindio.proyecto.repositories.prestamosTemporal;

import co.edu.uniquindio.proyecto.model.prestamosTemporal.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SucursalRepo extends JpaRepository<Sucursal, Integer> {

    // Encuentra sucursales por nombre
    List<Sucursal> findByNombre(String nombre);

    // Encuentra sucursales por el municipio asociado
    List<Sucursal> findByMunicipio_IdMunicipio(int idMunicipio);
}
