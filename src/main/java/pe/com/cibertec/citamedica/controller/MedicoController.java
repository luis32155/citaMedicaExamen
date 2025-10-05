package pe.com.cibertec.citamedica.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.com.cibertec.citamedica.dto.request.MedicoRequest;
import pe.com.cibertec.citamedica.dto.response.MedicoResponse;
import pe.com.cibertec.citamedica.service.MedicoService;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoService service;

    @GetMapping
    public List<MedicoResponse> listar() {
        return service.listar();
    }

    @GetMapping("/especialidad/{id}")
    public List<MedicoResponse> porEspecialidad(@PathVariable("id") Long especialidadId) {
        return service.listarPorEspecialidad(especialidadId);
    }

    @PostMapping
    public MedicoResponse crear(@RequestBody MedicoRequest request) {
        return service.crear(request);
    }

    @PutMapping("/{id}")
    public MedicoResponse actualizar(@PathVariable Long id, @RequestBody MedicoRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}

