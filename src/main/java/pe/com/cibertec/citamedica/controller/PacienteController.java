package pe.com.cibertec.citamedica.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.com.cibertec.citamedica.dto.request.PacienteRequest;
import pe.com.cibertec.citamedica.dto.response.PacienteResponse;
import pe.com.cibertec.citamedica.service.PacienteService;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService service;

    @GetMapping
    public List<PacienteResponse> listar() {
        return service.listar();
    }

    @PostMapping
    public PacienteResponse crear(@RequestBody PacienteRequest request) {
        return service.crear(request);
    }

    @PutMapping("/{id}")
    public PacienteResponse actualizar(@PathVariable Long id, @RequestBody PacienteRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
