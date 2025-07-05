package backEnd.controller;

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
@Tag(name = "Socios", description = "Operaciones relacionadas con socios")
public class SocioController {

    @Autowired
    private SocioService socioService;

    // GET /socios?apellido=...&edad=...
    @GetMapping
    @Operation(
            summary = "Listar socios con filtros",
            description = "Permite obtener un listado de todos los socios registrados en el club con la posibilidad de aplicar filtros para refinar la búsqueda.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listado de socios filtrado correctamente")
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
    @PostMapping
    @Operation(
            summary = "Alta de un socio",
            description = "Permite dar de alta a socios sin confirmación. Requiere los datos completos del socio en formato JSON según el esquema Socio.",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Socio.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Socio creado exitosamente")
            }
    )
    public ResponseEntity<Socio> agregarSocio(@Valid @RequestBody Socio socio) {
        Socio nuevo = socioService.agregarSocio(socio);
        return ResponseEntity.ok(nuevo);
    }


    // GET /socios/{dni}
    @GetMapping("/{dni}")
    @Operation(
            summary = "Consultar socio por DNI",
            description = "Realiza consultas partiendo del DNI del socio. El DNI no debe estar con puntos.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Socio encontrado"),
                    @ApiResponse(responseCode = "404", description = "Socio no encontrado")
            }
    )
    public ResponseEntity<Socio> obtenerSocioByDNI(@PathVariable String dni) {
        Socio socio = socioService.obtenerSocioByDNI(dni);
        if (socio != null) {
            return ResponseEntity.ok(socio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{dni}")
    @Operation(
            summary = "Baja de socio",
            description = "Permite eliminar un socio del sistema del club de forma permanente.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Socio eliminado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Socio no encontrado")
            }
    )
    public ResponseEntity<Void> eliminarSocioByDNI(@PathVariable String dni) {
        boolean eliminado = socioService.eliminarSocioByDNI(dni);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
