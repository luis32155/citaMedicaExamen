package pe.com.cibertec.citamedica.mapper;

import lombok.experimental.UtilityClass;
import pe.com.cibertec.citamedica.dto.request.CitaRequest;
import pe.com.cibertec.citamedica.dto.response.CitaResponse;
import pe.com.cibertec.citamedica.entity.*;
import java.time.LocalDateTime;
import java.util.Optional;

@UtilityClass
public class CitaMapperUtil {

    
    public static CitaResponse toResponse( Cita e) {
        return Optional.ofNullable(e)
                .map(x -> CitaResponse.builder()
                        .id(x.getId())
                        .fechaHora(x.getFechaHora())
                        .estado(Optional.ofNullable(x.getEstado()).map(Enum::name).orElse(null))
                        .pacienteId(Optional.ofNullable(x.getPaciente()).map(Paciente::getId).orElse(null))
                        .medicoId(Optional.ofNullable(x.getMedico()).map(Medico::getId).orElse(null))
                        .build())
                .orElse(null);
    }

    
    public static Cita fromRequest( CitaRequest r) {
        return Optional.ofNullable(r)
                .map(x -> Cita.builder()
                        .fechaHora(x.getFechaHora())
                        .estado(EstadoCita.RESERVADA)
                        .paciente(Optional.ofNullable(x.getPacienteId()).map(id -> Paciente.builder().id(id).build()).orElse(null))
                        .medico(Optional.ofNullable(x.getMedicoId()).map(id -> Medico.builder().id(id).build()).orElse(null))
                        .build())
                .orElse(null);
    }

    public static void updateEntityFecha(Cita e,  LocalDateTime nuevaFechaHora) {
        Optional.ofNullable(nuevaFechaHora).ifPresent(e::setFechaHora);
    }
}
