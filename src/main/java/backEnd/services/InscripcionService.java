package backEnd.services;

import backEnd.entities.Actividad;
import backEnd.entities.Socio;
import backEnd.repositories.ActividadRepository;
import backEnd.repositories.SocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InscripcionService {

    @Autowired
    private SocioRepository socioRepository;

    @Autowired
    private ActividadRepository actividadRepository;

    public boolean inscribir(Socio socio, Actividad actividad) {
        return actividad.agregarInscripcion(socio);
    }

    public boolean eliminarInscripcion(Socio socio, Actividad actividad) {
        return actividad.eliminarInscripcion(socio);
    }


        public boolean puedeInscribirse(String dniSocio, String nombreActividad) {
            Optional<Socio> socioOpt = socioRepository.findById(dniSocio);
            Optional<Actividad> actividadOpt = Optional.ofNullable(actividadRepository.findByNombre(nombreActividad));

            // Mostrar por consola si están presentes
            socioOpt.ifPresent(socio -> System.out.println("Socio encontrado: " + socio));
            actividadOpt.ifPresent(actividad -> System.out.println("Actividad encontrada: " + actividad));

            if (socioOpt.isEmpty() || actividadOpt.isEmpty()) {
                return false;
            }

            Socio socio = socioOpt.get();
            Actividad actividad = actividadOpt.get();

            int edad = socio.getEdad();

            if (edad < 18 && !actividad.isPermiteMenores()) {
                return false;
            }

            return true;
        }

}

