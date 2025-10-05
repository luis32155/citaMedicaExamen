package pe.com.cibertec.citamedica.mapper;

import lombok.experimental.UtilityClass;
import pe.com.cibertec.citamedica.dto.response.EspecialidadResponse;
import pe.com.cibertec.citamedica.entity.Especialidad;
import java.util.Optional;

@UtilityClass
public class EspecialidadMapperUtil {

    
    public static EspecialidadResponse toResponse(Especialidad e) {
        return Optional.ofNullable(e)
                .map(x -> EspecialidadResponse.builder()
                        .id(x.getId())
                        .nombre(x.getNombre())
                        .build())
                .orElse(null);
    }

    
    public static Especialidad ref( Long id) {
        return Optional.ofNullable(id)
                .map(x -> Especialidad.builder().id(x).build())
                .orElse(null);
    }
}
