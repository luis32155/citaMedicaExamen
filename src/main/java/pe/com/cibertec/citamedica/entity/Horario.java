package pe.com.cibertec.citamedica.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "horario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;
    @Column(nullable = false)
    private LocalTime horaInicio;
    @Column(nullable = false)
    private LocalTime horaFin;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;
}
