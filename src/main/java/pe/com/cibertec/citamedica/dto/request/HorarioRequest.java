package pe.com.cibertec.citamedica.dto.request;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HorarioRequest {
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Long medicoId;
}

