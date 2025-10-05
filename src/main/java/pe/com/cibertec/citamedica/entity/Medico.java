package pe.com.cibertec.citamedica.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medico")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "especialidad_id")
    private Especialidad especialidad;
}
