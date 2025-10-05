package pe.com.cibertec.citamedica.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaResponse {
    private Long id;
    private LocalDateTime fechaHora;
    private String estado;
    private Long pacienteId;
    private Long medicoId;
}
