package pe.com.cibertec.citamedica.service;


import pe.com.cibertec.citamedica.dto.request.PacienteRequest;
import pe.com.cibertec.citamedica.dto.response.PacienteResponse;

import java.util.List;

public interface PacienteService {
    List<PacienteResponse> listar();
    PacienteResponse crear(PacienteRequest request);
    PacienteResponse actualizar(Long id, PacienteRequest request);
    void eliminar(Long id);
}

