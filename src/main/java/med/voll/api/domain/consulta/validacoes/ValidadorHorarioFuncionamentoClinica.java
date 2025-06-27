package med.voll.api.domain.consulta.validacoes;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.dataHora();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        if (domingo) throw new ValidationException("A clinica não funciona aos domingos");
        var antesDeAbrir = dataConsulta.getHour() < 7;
        if(antesDeAbrir) throw new ValidationException("Nosso horários de atendimento é a partir das 07:00");
        var depoisDeFechar = dataConsulta.getHour() > 18;
        if (depoisDeFechar) throw new ValidationException("Nosso horário de atendimento se encerra as 19:00, as consunta só poder ser agendadas ate as 18:00");

//        if (domingo || antesDeAbrir || depoisDeFechar) {
//            throw new ValidationException("Consulta fora do funcionamento da clínica");
//        }
    }
}
