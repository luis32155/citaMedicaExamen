package pe.com.cibertec.citamedica.mapper;

import lombok.experimental.UtilityClass;
import pe.com.cibertec.citamedica.dto.request.HorarioRequest;
import pe.com.cibertec.citamedica.dto.response.HorarioResponse;
import pe.com.cibertec.citamedica.entity.Horario;
import pe.com.cibertec.citamedica.entity.Medico;

import java.util.Optional;

@UtilityClass
public class HorarioMapperUtil {


    public static HorarioResponse toResponse(Horario e) {
        return Optional.ofNullable(e)
                .map(x -> HorarioResponse.builder()
                        .id(x.getId())
                        .fecha(x.getFecha())
                        .horaInicio(x.getHoraInicio())
                        .horaFin(x.getHoraFin())
                        .medicoId(Optional.ofNullable(x.getMedico()).map(Medico::getId).orElse(null))
                        .build())
                .orElse(null);
    }


    public static Horario fromRequest(HorarioRequest r) {
        return Optional.ofNullable(r)
                .map(x -> Horario.builder()
                        .fecha(x.getFecha())
                        .horaInicio(x.getHoraInicio())
                        .horaFin(x.getHoraFin())
                        .medico(Optional.ofNullable(x.getMedicoId()).map(id -> Medico.builder().id(id).build()).orElse(null))
                        .build())
                .orElse(null);
    }

    public static void updateEntity(Horario e, HorarioRequest r) {
        Optional.ofNullable(r).ifPresent(x -> {
            e.setFecha(x.getFecha());
            e.setHoraInicio(x.getHoraInicio());
            e.setHoraFin(x.getHoraFin());
            e.setMedico(Optional.ofNullable(x.getMedicoId())
                    .map(id -> Optional.ofNullable(e.getMedico()).orElseGet(Medico::new))
                    .map(m -> {
                        m.setId(x.getMedicoId());
                        return m;
                    })
                    .orElse(null));
        });
    }
}

