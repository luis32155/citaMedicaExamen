package pe.com.cibertec.citamedica.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.com.cibertec.citamedica.dto.request.MedicoRequest;
import pe.com.cibertec.citamedica.dto.response.MedicoResponse;
import pe.com.cibertec.citamedica.entity.Medico;
import pe.com.cibertec.citamedica.mapper.MedicoMapperUtil;
import pe.com.cibertec.citamedica.repository.MedicoRepository;
import pe.com.cibertec.citamedica.service.MedicoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository repo;

    @Override
    public List<MedicoResponse> listar() {
        return repo.findAll().stream().map(MedicoMapperUtil::toResponse).toList();
    }

    @Override
    public List<MedicoResponse> listarPorEspecialidad(Long especialidadId) {
        return repo.findByEspecialidadId(especialidadId).stream().map(MedicoMapperUtil::toResponse).toList();
    }

    @Override
    @Transactional
    public MedicoResponse crear(MedicoRequest request) {
        Medico entity = MedicoMapperUtil.fromRequest(request);
        return MedicoMapperUtil.toResponse(repo.save(entity));
    }

    @Override
    @Transactional
    public MedicoResponse actualizar(Long id, MedicoRequest request) {
        Medico entity = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico no encontrado"));
        MedicoMapperUtil.updateEntity(entity, request);
        return MedicoMapperUtil.toResponse(repo.save(entity));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!repo.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico no encontrado");
        repo.deleteById(id);
    }
}

