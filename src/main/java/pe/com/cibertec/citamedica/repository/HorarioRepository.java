package pe.com.cibertec.citamedica.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.cibertec.citamedica.entity.Horario;

import java.time.LocalDate;
import java.util.List;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
    List<Horario> findByMedicoIdAndFecha(Long medicoId, LocalDate fecha);
}
