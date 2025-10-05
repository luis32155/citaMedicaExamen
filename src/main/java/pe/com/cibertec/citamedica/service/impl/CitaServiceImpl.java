package pe.com.cibertec.citamedica.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pe.com.cibertec.citamedica.dto.request.CitaRequest;
import pe.com.cibertec.citamedica.dto.response.CitaResponse;
import pe.com.cibertec.citamedica.entity.Cita;
import pe.com.cibertec.citamedica.entity.EstadoCita;
import pe.com.cibertec.citamedica.entity.Horario;
import pe.com.cibertec.citamedica.mapper.CitaMapperUtil;
import pe.com.cibertec.citamedica.repository.CitaRepository;
import pe.com.cibertec.citamedica.repository.HorarioRepository;
import pe.com.cibertec.citamedica.repository.MedicoRepository;
import pe.com.cibertec.citamedica.repository.PacienteRepository;
import pe.com.cibertec.citamedica.service.CitaService;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepo;
    private final PacienteRepository pacienteRepo;
    private final MedicoRepository medicoRepo;
    private final HorarioRepository horarioRepo;

    @Override
    @Transactional
    public CitaResponse reservar(CitaRequest request) {
        var paciente = pacienteRepo.findById(request.getPacienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no existe"));
        var medico = medicoRepo.findById(request.getMedicoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico no existe"));

        if (citaRepo.existsByMedicoIdAndFechaHora(medico.getId(), request.getFechaHora())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El médico ya tiene una cita en esa hora");
        }

        var fecha = request.getFechaHora().toLocalDate();
        LocalTime hora = request.getFechaHora().toLocalTime();

        List<Horario> horarios = horarioRepo.findByMedicoIdAndFecha(medico.getId(), fecha);
        boolean dentroHorario = horarios.stream().anyMatch(h ->
                (!hora.isBefore(h.getHoraInicio())) && (hora.isBefore(h.getHoraFin()))
        );
        if (!dentroHorario) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hora fuera del horario del médico");
        }

        Cita entity = CitaMapperUtil.fromRequest(request);
        entity.setPaciente(paciente);
        entity.setMedico(medico);
        entity.setEstado(EstadoCita.RESERVADA);

        return CitaMapperUtil.toResponse(citaRepo.save(entity));
    }

    @Override
    @Transactional
    public CitaResponse cancelar(Long citaId) {
        Cita entity = citaRepo.findById(citaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no existe"));
        entity.setEstado(EstadoCita.CANCELADA);
        return CitaMapperUtil.toResponse(citaRepo.save(entity));
    }

    @Override
    public List<CitaResponse> listarPorPaciente(Long pacienteId) {
        return citaRepo.findByPacienteId(pacienteId).stream().map(CitaMapperUtil::toResponse).toList();
    }

    @Override
    public List<CitaResponse> listarPorMedico(Long medicoId) {
        return citaRepo.findByMedicoId(medicoId).stream().map(CitaMapperUtil::toResponse).toList();
    }
}

