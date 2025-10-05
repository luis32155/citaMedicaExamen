package pe.com.cibertec.citamedica.service;


import pe.com.cibertec.citamedica.dto.request.MedicoRequest;
import pe.com.cibertec.citamedica.dto.response.MedicoResponse;

import java.util.List;

public interface MedicoService {
    List<MedicoResponse> listar();

    List<MedicoResponse> listarPorEspecialidad(Long especialidadId);

    MedicoResponse crear(MedicoRequest request);

    MedicoResponse actualizar(Long id, MedicoRequest request);

    void eliminar(Long id);
}

