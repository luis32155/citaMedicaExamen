package pe.com.cibertec.citamedica.service;


import pe.com.cibertec.citamedica.dto.request.HorarioRequest;
import pe.com.cibertec.citamedica.dto.response.HorarioResponse;

import java.time.LocalDate;
import java.util.List;

public interface HorarioService {
    List<HorarioResponse> obtenerHorariosDisponibles(Long medicoId, LocalDate fecha);
    HorarioResponse crear(HorarioRequest request);
    void eliminar(Long id);
}
