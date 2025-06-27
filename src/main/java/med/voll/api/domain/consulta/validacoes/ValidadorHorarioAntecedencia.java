package med.voll.api.domain.consulta.validacoes;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
 class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.dataHora();
        var horaAtual = LocalDateTime.now();
        var diferencaHora = Duration.between(horaAtual, dataConsulta).toMinutes();
        if (diferencaHora < 30){
            throw new ValidationException("A consulta deve ser agendada com uma antecedência minima de 30 minutos!");
        }
    }
}
