package pe.com.cibertec.citamedica.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.cibertec.citamedica.entity.Cita;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByPacienteId(Long pacienteId);

    List<Cita> findByMedicoId(Long medicoId);

    boolean existsByMedicoIdAndFechaHora(Long medicoId, LocalDateTime fechaHora);
}
