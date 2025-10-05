package pe.com.cibertec.citamedica.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.com.cibertec.citamedica.dto.request.HorarioRequest;
import pe.com.cibertec.citamedica.dto.response.HorarioResponse;
import pe.com.cibertec.citamedica.mapper.HorarioMapperUtil;
import pe.com.cibertec.citamedica.repository.HorarioRepository;
import pe.com.cibertec.citamedica.service.HorarioService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HorarioServiceImpl implements HorarioService {

    private final HorarioRepository repo;

    @Override
    public List<HorarioResponse> obtenerHorariosDisponibles(Long medicoId, LocalDate fecha) {
        return repo.findByMedicoIdAndFecha(medicoId, fecha).stream()
                .map(HorarioMapperUtil::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public HorarioResponse crear(HorarioRequest request) {
        return HorarioMapperUtil.toResponse(repo.save(HorarioMapperUtil.fromRequest(request)));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
