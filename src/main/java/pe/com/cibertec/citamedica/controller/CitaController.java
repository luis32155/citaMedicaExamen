package pe.com.cibertec.citamedica.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.com.cibertec.citamedica.dto.request.CitaRequest;
import pe.com.cibertec.citamedica.dto.response.CitaResponse;
import pe.com.cibertec.citamedica.service.CitaService;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController {

    private final CitaService service;

    @PostMapping
    public CitaResponse reservar(@RequestBody CitaRequest request) {
        return service.reservar(request);
    }

    @DeleteMapping("/{id}")
    public CitaResponse cancelar(@PathVariable Long id) {
        return service.cancelar(id);
    }

    @GetMapping("/paciente/{id}")
    public List<CitaResponse> porPaciente(@PathVariable("id") Long pacienteId) {
        return service.listarPorPaciente(pacienteId);
    }

    @GetMapping("/medico/{id}")
    public List<CitaResponse> porMedico(@PathVariable("id") Long medicoId) {
        return service.listarPorMedico(medicoId);
    }
}
