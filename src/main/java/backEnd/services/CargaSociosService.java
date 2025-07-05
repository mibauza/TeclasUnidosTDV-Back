package backEnd.services;

import backEnd.entities.Socio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;

@Service
public class CargaSociosService {

    @Autowired
    private SocioService socioService;

    public void cargarDesdeCSV(String rutaArchivo) {
        try (Reader reader = new FileReader(rutaArchivo)) {
            CsvToBean<Socio> csvToBean = new CsvToBeanBuilder<Socio>(reader)
                    .withType(Socio.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<Socio> socios = csvToBean.parse();

            for (Socio socio : socios) {
                socioService.agregarSocio(socio);
            }

            System.out.println("Carga CSV completada. Total socios cargados: " + socios.size());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al cargar el CSV: " + e.getMessage());
        }
    }
}

