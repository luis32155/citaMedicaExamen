package pe.com.cibertec.citamedica.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PacienteResponse {
    private Long id;
    private String nombre;
    private String dni;
    private String telefono;
}

