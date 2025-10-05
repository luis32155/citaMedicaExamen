package pe.com.cibertec.citamedica.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.com.cibertec.citamedica.dto.request.PacienteRequest;
import pe.com.cibertec.citamedica.dto.response.PacienteResponse;
import pe.com.cibertec.citamedica.entity.Paciente;
import pe.com.cibertec.citamedica.mapper.PacienteMapperUtil;
import pe.com.cibertec.citamedica.repository.PacienteRepository;
import pe.com.cibertec.citamedica.service.PacienteService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository repo;

    @Override
    public List<PacienteResponse> listar() {
        return repo.findAll().stream().map(PacienteMapperUtil::toResponse).toList();
    }

    @Override
    @Transactional
    public PacienteResponse crear(PacienteRequest request) {
        Paciente entity = PacienteMapperUtil.fromRequest(request);
        return PacienteMapperUtil.toResponse(repo.save(entity));
    }

    @Override
    @Transactional
    public PacienteResponse actualizar(Long id, PacienteRequest request) {
        Paciente entity = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));
        PacienteMapperUtil.updateEntity(entity, request);
        return PacienteMapperUtil.toResponse(repo.save(entity));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!repo.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado");
        repo.deleteById(id);
    }
}

