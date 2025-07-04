package med.voll.api.domain.consulta.validacoes;


import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteAtivo = pacienteRepository.existsByIdAndAtivoTrue(dados.idPaciente());
        if (!pacienteAtivo) {
            throw new ValidationException("A consulta não pode ser agendada com paciente invativo!");
        }
    }
}
