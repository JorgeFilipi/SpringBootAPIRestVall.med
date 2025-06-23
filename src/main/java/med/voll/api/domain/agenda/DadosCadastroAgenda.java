package med.voll.api.domain.agenda;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record DadosCadastroAgenda(
        @NotNull LocalDateTime dataHora,
        @NotNull Long medicoId,
        @NotNull Long pacienteId
) {
    public Especialidade especialidade() {
        return null;
    }
}
