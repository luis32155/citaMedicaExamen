package pe.com.cibertec.citamedica.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.cibertec.citamedica.entity.Especialidad;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {
}
