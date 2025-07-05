package backEnd.repositories;

import backEnd.entities.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long> {

    // Ejemplo de método personalizado si lo necesitas
    boolean existsByNombre(String nombre);

    Actividad findByNombre(String nombre);
}



