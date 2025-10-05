package pe.com.cibertec.citamedica.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.cibertec.citamedica.dto.response.EspecialidadResponse;
import pe.com.cibertec.citamedica.mapper.EspecialidadMapperUtil;
import pe.com.cibertec.citamedica.repository.EspecialidadRepository;
import pe.com.cibertec.citamedica.service.EspecialidadService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EspecialidadServiceImpl implements EspecialidadService {

    private final EspecialidadRepository repo;

    @Override
    public List<EspecialidadResponse> listar() {
        return repo.findAll().stream().map(EspecialidadMapperUtil::toResponse).toList();
    }
}

