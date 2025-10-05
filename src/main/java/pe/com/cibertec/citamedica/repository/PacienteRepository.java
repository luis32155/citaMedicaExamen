package pe.com.cibertec.citamedica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.cibertec.citamedica.entity.*;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByDni(String dni);

    boolean existsByDni(String dni);
}

