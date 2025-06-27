package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByPacienteAndDataHoraBetween(Paciente paciente, LocalDateTime inicio, LocalDateTime fim);

    boolean existsByMedicoAndDataHora(Medico medico, LocalDateTime dataHora);

//    boolean existsByPacienteIdAndDataBetween( Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    boolean existsByPacienteIdAndDataHoraBetween(Long idPaciente, LocalDateTime inicio, LocalDateTime fim);

    boolean existsByMedicoIdAndDataHora(Long medicoId, LocalDateTime dataHora);

}
