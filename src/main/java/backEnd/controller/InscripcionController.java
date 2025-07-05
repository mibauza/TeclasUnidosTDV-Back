package backEnd.controller;

import backEnd.services.InscripcionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inscripcion")
@Tag(name = "Inscripción", description = "Operaciones relacionadas con inscripciones de socios a actividades")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @Operation(
            summary = "Verifica si un socio puede inscribirse a una actividad",
            description = "Devuelve `true` si el socio con el DNI proporcionado puede inscribirse a la actividad especificada. En caso contrario, devuelve `false`.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Verificación realizada correctamente"),
                    @ApiResponse(responseCode = "400", description = "Parámetros inválidos"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    @GetMapping("/verificar")
    public ResponseEntity<String> verificarInscripcion(
            @Parameter(description = "DNI del socio a verificar", example = "12345678")
            @RequestParam String dni,
            @Parameter(description = "Nombre de la actividad", example = "Fútbol Infantil")
            @RequestParam String actividad) {

        boolean puede = inscripcionService.puedeInscribirse(dni, actividad);
        if (puede) {
            return ResponseEntity.ok("El socio puede inscribirse en la actividad.");
        } else {
            return ResponseEntity.ok("El socio NO cumple con los requisitos de inscripción.");
        }
    }
}

