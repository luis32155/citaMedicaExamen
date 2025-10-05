package pe.com.cibertec.citamedica.service;


import pe.com.cibertec.citamedica.dto.request.CitaRequest;
import pe.com.cibertec.citamedica.dto.response.CitaResponse;

import java.util.List;

public interface CitaService {
    CitaResponse reservar(CitaRequest request);

    CitaResponse cancelar(Long citaId);

    List<CitaResponse> listarPorPaciente(Long pacienteId);

    List<CitaResponse> listarPorMedico(Long medicoId);
}

