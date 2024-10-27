package co.edu.uniquindio.proyecto.repositories.prestamosTemporal;

import co.edu.uniquindio.proyecto.model.prestamosTemporal.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipioRepo extends JpaRepository<Municipio, Integer> {

    // Encuentra municipios por nombre
    List<Municipio> findByNombre(String nombre);

    // Encuentra municipios por el ID del departamento
    List<Municipio> findByDepartamento_IdDepartamento(int idDepartamento);
}
