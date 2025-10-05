package pe.com.cibertec.citamedica.dto.response;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicoResponse {
    private Long id;
    private String nombre;
    private Long especialidadId;
    private String especialidadNombre;
}
