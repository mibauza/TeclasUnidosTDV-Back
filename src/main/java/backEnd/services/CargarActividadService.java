package backEnd.services;

import backEnd.entities.Actividad;
import backEnd.repositories.ActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class CargarActividadService {

    @Autowired
    private ActividadRepository actividadRepository;

    public void cargarDesdeStream(InputStream inputStream) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = reader.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false; // Salta la cabecera
                    continue;
                }

                String[] campos = linea.split(",");
                if (campos.length < 7) {
                    throw new IllegalArgumentException("Línea malformada: " + linea);
                }

                String nombre = campos[0].trim();
                String encargado = campos[1].trim();
                String horario = campos[2].trim();
                int edadMinima = Integer.parseInt(campos[3].trim());
                String lugar = campos[4].trim();
                int cupo = Integer.parseInt(campos[5].trim());
                boolean permiteMenores = Boolean.parseBoolean(campos[6].trim());

                if (actividadRepository.findByNombre(nombre) == null) {
                    Actividad actividad = new Actividad(nombre, encargado, horario, edadMinima, lugar, cupo, permiteMenores);
                    actividadRepository.save(actividad);
                } else {
                    System.out.println("Actividad duplicada (no insertada): " + nombre);
                }

            }
        } catch (Exception e) {
            throw new Exception("Error procesando el CSV de actividades: " + e.getMessage(), e);
        }
    }
}
