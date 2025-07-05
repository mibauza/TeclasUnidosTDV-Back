package backEnd.repositories;

import backEnd.entities.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocioRepository extends JpaRepository<Socio, String> {

    // Buscar socios por apellido
    List<Socio> findByApellido(String apellido);

    // Buscar socios por edad
    List<Socio> findByEdad(Integer edad);

    // Buscar socios por apellido y edad juntos
    List<Socio> findByApellidoAndEdad(String apellido, Integer edad);
}
