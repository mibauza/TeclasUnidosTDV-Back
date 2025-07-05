package backEnd.controller;

import backEnd.DTOs.SocioDTO;
import backEnd.entities.Socio;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import backEnd.services.SocioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/socios")
@Tag(name = "Club Deportivo API", description = "API Club Deportivo (1.0.0)")
public class SocioController {

    @Autowired
    private SocioService socioService;

    // GET /socios?apellido=...&edad=...
    @GetMapping
    // --- Socios ---

    @Operation(summary = "Listar socios con filtros",
            parameters = {
                    @Parameter(name = "apellido", description = "Apellido del socio", schema = @Schema(type = "string")),
                    @Parameter(name = "edad", description = "Edad del socio", schema = @Schema(type = "integer"))
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de socios")
            }
    )
    public ResponseEntity<List<Socio>> listarSociosByPharams(
            @Parameter(description = "Filtro por apellido del socio")
            @RequestParam(required = false) String apellido,
            @Parameter(description = "Filtro por edad específica del socio")
            @RequestParam(required = false) Integer edad) {
        List<Socio> socios = socioService.listarSociosByPharams(apellido, edad);
        return ResponseEntity.ok(socios);
    }

    // POST /socios

    @Operation(summary = "Alta de socio",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SocioDTO.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Socio creado")
            }
    )
    @PostMapping
    public ResponseEntity<Socio> agregarSocio(@Valid @RequestBody Socio socio) {
        Socio nuevo = socioService.agregarSocio(socio);
        return ResponseEntity.ok(nuevo);
    }


    // GET /socios/{dni}
    @Operation(summary = "Consultar socio por DNI",
            parameters = {
                    @Parameter(name = "dni", description = "DNI del socio", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Socio encontrado")
            }
    )
    @GetMapping("/{dni}")
    public ResponseEntity<Socio> obtenerSocioByDNI(@PathVariable String dni) {
        Socio socio = socioService.obtenerSocioByDNI(dni);
        if (socio != null) {
            return ResponseEntity.ok(socio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Baja de socio",
            parameters = {
                    @Parameter(name = "dni", description = "DNI del socio", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Socio eliminado")
            }
    )
    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> eliminarSocioByDNI(@PathVariable String dni) {
        boolean eliminado = socioService.eliminarSocioByDNI(dni);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
