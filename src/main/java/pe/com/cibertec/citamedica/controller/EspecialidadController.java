package pe.com.cibertec.citamedica.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.com.cibertec.citamedica.dto.response.EspecialidadResponse;
import pe.com.cibertec.citamedica.service.EspecialidadService;

import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
@RequiredArgsConstructor
public class EspecialidadController {

    private final EspecialidadService service;

    @GetMapping
    public List<EspecialidadResponse> listar() {
        return service.listar();
    }
}
