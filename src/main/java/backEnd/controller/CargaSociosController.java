package backEnd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import backEnd.services.CargaSociosService;

@SpringBootApplication
public class CargaSociosController implements CommandLineRunner{
    @Autowired
    private CargaSociosService cargaSociosService;

    public static void main(String[] args) {
        SpringApplication.run(CargaSociosController.class, args);
    }

    @Override
    public void run(String... args) {
        // Ruta al archivo CSV
        String ruta = "src/main/resources/socios.csv";
        cargaSociosService.cargarDesdeCSV(ruta);
    }
}
