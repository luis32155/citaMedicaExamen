package pe.com.cibertec.citamedica.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.cibertec.citamedica.entity.Medico;
import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    List<Medico> findByEspecialidadId(Long especialidadId);
}

