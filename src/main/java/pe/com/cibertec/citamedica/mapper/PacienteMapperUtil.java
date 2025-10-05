package pe.com.cibertec.citamedica.mapper;

import lombok.experimental.UtilityClass;
import pe.com.cibertec.citamedica.dto.request.PacienteRequest;
import pe.com.cibertec.citamedica.dto.response.PacienteResponse;
import pe.com.cibertec.citamedica.entity.Paciente;

import java.util.Optional;

@UtilityClass
public class PacienteMapperUtil {

    
    public static PacienteResponse toResponse( Paciente e) {
        return Optional.ofNullable(e)
                .map(x -> PacienteResponse.builder()
                        .id(x.getId())
                        .nombre(x.getNombre())
                        .dni(x.getDni())
                        .telefono(x.getTelefono())
                        .build())
                .orElse(null);
    }

    
    public static Paciente fromRequest( PacienteRequest r) {
        return Optional.ofNullable(r)
                .map(x -> Paciente.builder()
                        .nombre(x.getNombre())
                        .dni(x.getDni())
                        .telefono(x.getTelefono())
                        .build())
                .orElse(null);
    }

    public static void updateEntity(Paciente e,  PacienteRequest r) {
        Optional.ofNullable(r).ifPresent(x -> {
            e.setNombre(x.getNombre());
            e.setDni(x.getDni());
            e.setTelefono(x.getTelefono());
        });
    }
}

