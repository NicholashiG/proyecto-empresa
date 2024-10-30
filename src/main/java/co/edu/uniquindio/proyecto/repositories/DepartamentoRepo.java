package co.edu.uniquindio.proyecto.repositories;

import co.edu.uniquindio.proyecto.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartamentoRepo extends JpaRepository<Departamento, Integer> {
    // Métodos de consulta personalizados pueden añadirse aquí si es necesario

    // Encuentra un departamento por nombre (asumiendo que el nombre es único)
    Optional<Departamento> findByNombre(String nombre);

    // Verifica si existe un departamento con un nombre específico
    boolean existsByNombre(String nombre);


}