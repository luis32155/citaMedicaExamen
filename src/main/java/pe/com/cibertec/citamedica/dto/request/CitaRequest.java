package pe.com.cibertec.citamedica.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaRequest {
    private Long pacienteId;
    private Long medicoId;
    private LocalDateTime fechaHora;
}

