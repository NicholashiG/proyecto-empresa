`JpaRepository` en Spring Data JPA proporciona una serie de métodos CRUD (Crear, Leer, Actualizar, Borrar) ya integrados que permiten interactuar con la base de datos sin escribir código adicional. A continuación, te detallo los métodos principales que `JpaRepository` incluye y cómo se usan:

### Métodos Principales de `JpaRepository`

1. **`T save(T entity)`**
   Guarda o actualiza una entidad. Si la entidad ya existe (tiene un ID que coincide con una entrada existente), realiza una actualización. Si no, crea una nueva entrada.

2. **`List<T> findAll()`**
   Recupera todas las entidades de un tipo específico de la base de datos.

3. **`Optional<T> findById(ID id)`**
   Encuentra una entidad por su ID y devuelve un `Optional` que contendrá la entidad si existe o estará vacío si no se encuentra.

4. **`boolean existsById(ID id)`**
   Verifica si existe una entidad con un ID específico en la base de datos.

5. **`long count()`**
   Devuelve el número total de entidades de ese tipo en la base de datos.

6. **`void deleteById(ID id)`**
   Elimina la entidad con el ID especificado. Si no se encuentra, no realiza ninguna operación.

7. **`void delete(T entity)`**
   Elimina una entidad específica de la base de datos.

8. **`void deleteAll(Iterable<? extends T> entities)`**
   Elimina todas las entidades proporcionadas en una colección.

9. **`void deleteAll()`**
   Elimina todas las entidades de ese tipo en la base de datos.

### Métodos para Operaciones en Lotes

- **`List<T> saveAll(Iterable<T> entities)`**
  Guarda o actualiza en la base de datos una lista de entidades en una sola operación de lote, lo que puede mejorar la eficiencia cuando se manipulan varias entidades a la vez.

- **`void deleteAllInBatch()`**
  Elimina todas las entidades en una sola operación de lote.

- **`void deleteAllByIdInBatch(Iterable<ID> ids)`**
  Elimina todas las entidades especificadas por sus IDs en una sola operación de lote.

### Otros Métodos de Consulta

- **`List<T> findAllById(Iterable<ID> ids)`**
  Encuentra todas las entidades que tengan uno de los IDs en la lista proporcionada.

- **`Page<T> findAll(Pageable pageable)`**
  Recupera todas las entidades con soporte para paginación, devolviendo un objeto `Page` que contiene la lista de resultados y detalles sobre la página.

- **`List<T> findAll(Sort sort)`**
  Devuelve todas las entidades ordenadas de acuerdo con el criterio de ordenación especificado.

### Ejemplo de Uso

Para un repositorio `AuditoriaRepository`, algunos ejemplos de uso de estos métodos serían:

```java
@Autowired
private AuditoriaRepository auditoriaRepository;

// Guardar una nueva auditoría
Auditoria auditoria = new Auditoria(1, "2024-10-25", "10:30", "Crear usuario", usuario);
auditoriaRepository.save(auditoria);

// Buscar todas las auditorías
List<Auditoria> todasLasAuditorias = auditoriaRepository.findAll();

// Buscar una auditoría por ID
Optional<Auditoria> auditoriaOptional = auditoriaRepository.findById(1);

// Eliminar una auditoría por ID
auditoriaRepository.deleteById(1);
