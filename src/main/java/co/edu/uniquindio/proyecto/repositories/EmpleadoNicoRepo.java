package co.edu.uniquindio.proyecto.repositories;

import co.edu.uniquindio.proyecto.model.EmpleadoNico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoNicoRepo extends JpaRepository<EmpleadoNico, Integer> {
    // Métodos de consulta personalizados pueden añadirse aquí

    // Encuentra empleados por apellido
    List<EmpleadoNico> findByApellido(String apellido);

    // Encuentra empleados por nombre
    List<EmpleadoNico> findByNombre(String nombre);

    // Encuentra empleados por tipo de empleado
    List<EmpleadoNico> findByTipoEmpleado_IdTipoEmpleado(int idTipoEmpleado);

    // Encuentra empleados por sucursal
    List<EmpleadoNico> findBySucursal_IdSucursal(int idSucursal);


}
