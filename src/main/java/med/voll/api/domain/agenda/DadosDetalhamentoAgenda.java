package med.voll.api.domain.agenda;

import java.time.LocalDateTime;

public record DadosDetalhamentoAgenda(
        Long id,
        LocalDateTime dataHora,
        String nomeMedico,
        String nomePaciente
) {
    public DadosDetalhamentoAgenda(Agenda agenda) {
        this(
                agenda.getId(),
                agenda.getDataHora(),
                agenda.getMedico().getNome(),
                agenda.getPaciente().getNome()
        );
    }
}
