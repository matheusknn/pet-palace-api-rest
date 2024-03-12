package pet.palace.api.domain.consulta.validacoes;

import org.springframework.stereotype.Component;
import pet.palace.api.domain.ValidacaoException;
import pet.palace.api.domain.consulta.DadosAgendarConsulta;

import java.time.DayOfWeek;

@Component
public class ValidarHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsultas {
    public void validar(DadosAgendarConsulta dados) {
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesAberturaClinica = dataConsulta.getHour() < 7;
        var depoisEncerramentoCliniva = dataConsulta.getHour() > 17;
        if (domingo || antesAberturaClinica || depoisEncerramentoCliniva) {
            throw new ValidacaoException("não é possível agendar consultas fora do horário de funcionamento");
        }
    }
}
