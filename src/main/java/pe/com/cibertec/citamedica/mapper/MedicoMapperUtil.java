package pe.com.cibertec.citamedica.mapper;

import lombok.experimental.UtilityClass;
import pe.com.cibertec.citamedica.dto.request.MedicoRequest;
import pe.com.cibertec.citamedica.dto.response.MedicoResponse;
import pe.com.cibertec.citamedica.entity.Medico;
import pe.com.cibertec.citamedica.entity.Especialidad;

import java.util.Optional;

@UtilityClass
public class MedicoMapperUtil {

    
    public static MedicoResponse toResponse(Medico e) {
        return Optional.ofNullable(e)
                .map(x -> {
                    Long espId = Optional.ofNullable(x.getEspecialidad()).map(Especialidad::getId).orElse(null);
                    String espNom = Optional.ofNullable(x.getEspecialidad()).map(Especialidad::getNombre).orElse(null);
                    return MedicoResponse.builder()
                            .id(x.getId())
                            .nombre(x.getNombre())
                            .especialidadId(espId)
                            .especialidadNombre(espNom)
                            .build();
                })
                .orElse(null);
    }

    
    public static Medico fromRequest(MedicoRequest r) {
        return Optional.ofNullable(r)
                .map(x -> Medico.builder()
                        .nombre(x.getNombre())
                        .especialidad(Optional.ofNullable(x.getEspecialidadId())
                                .map(id -> Especialidad.builder().id(id).build())
                                .orElse(null))
                        .build())
                .orElse(null);
    }

    public static void updateEntity(Medico e,  MedicoRequest r) {
        Optional.ofNullable(r).ifPresent(x -> {
            e.setNombre(x.getNombre());
            e.setEspecialidad(Optional.ofNullable(x.getEspecialidadId())
                    .map(id -> Optional.ofNullable(e.getEspecialidad()).orElseGet(Especialidad::new))
                    .map(es -> { es.setId(x.getEspecialidadId()); return es; })
                    .orElse(null));
        });
    }
}

