package backEnd.services;

import backEnd.entities.Socio;
import backEnd.repositories.SocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SocioService {

    @Autowired
    private SocioRepository socioRepository;

    /**
     * GET /socios con filtros
     */
    public List<Socio> listarSociosByPharams(String apellido, Integer edad) {
        if (apellido != null && edad != null) {
            return socioRepository.findByApellidoAndEdad(apellido, edad);
        } else if (apellido != null) {
            return socioRepository.findByApellido(apellido);
        } else if (edad != null) {
            return socioRepository.findByEdad(edad);
        } else {
            return socioRepository.findAll();
        }
    }

    /**
     * POST /socios
     */
    public Socio agregarSocio(Socio socio) {
        return socioRepository.save(socio);
    }

    /**
     * GET /socios/{dni}
     */
    public Socio obtenerSocioByDNI(String dni) {
        Optional<Socio> socio = socioRepository.findById(dni);
        return socio.orElse(null);
    }

    /**
     * DELETE /socios/{dni}
     */
    public boolean eliminarSocioByDNI(String dni) {
        if (socioRepository.existsById(dni)) {
            socioRepository.deleteById(dni);
            return true;
        } else {
            return false;
        }
    }
}
