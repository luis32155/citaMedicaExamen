package pe.com.cibertec.citamedica.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.com.cibertec.citamedica.dto.request.HorarioRequest;
import pe.com.cibertec.citamedica.dto.response.HorarioResponse;
import pe.com.cibertec.citamedica.service.HorarioService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/horarios")
@RequiredArgsConstructor
public class HorarioController {

    private final HorarioService service;

    @GetMapping("/{medicoId}")
    public List<HorarioResponse> disponibles(@PathVariable Long medicoId,
                                             @RequestParam(required = false) String fecha) {
        LocalDate f = (fecha != null) ? LocalDate.parse(fecha) : LocalDate.now();
        return service.obtenerHorariosDisponibles(medicoId, f);
    }

    @PostMapping
    public HorarioResponse crear(@RequestBody HorarioRequest request) {
        return service.crear(request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}

