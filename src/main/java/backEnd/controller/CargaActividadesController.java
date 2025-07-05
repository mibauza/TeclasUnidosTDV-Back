package backEnd.controller;

import backEnd.services.CargarActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@SpringBootApplication
public class CargaActividadesController implements CommandLineRunner {

    @Autowired
    private CargarActividadService cargarActividadService;

    public static void main(String[] args) {
        SpringApplication.run(CargaActividadesController.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            // Ruta dentro de resources
            File file = new ClassPathResource("actividades.csv").getFile();
            InputStream inputStream = new FileInputStream(file);

            // Cargar desde InputStream (emulando MultipartFile)
            cargarActividadService.cargarDesdeStream(inputStream);

            System.out.println("Actividades cargadas correctamente desde el archivo CSV.");
        } catch (Exception e) {
            System.err.println("Error al cargar actividades: " + e.getMessage());
        }
    }
}
