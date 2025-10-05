package pe.com.cibertec.citamedica.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicoRequest {
    private String nombre;
    private Long especialidadId;
}

