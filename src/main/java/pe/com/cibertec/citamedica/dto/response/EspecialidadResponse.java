package pe.com.cibertec.citamedica.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EspecialidadResponse {
    private Long id;
    private String nombre;
}
