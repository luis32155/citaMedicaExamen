package pe.com.cibertec.citamedica.dto.request;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PacienteRequest {
    private String nombre;
    private String dni;
    private String telefono;
}

