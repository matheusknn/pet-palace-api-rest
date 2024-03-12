package pet.palace.api.domain.consulta.validacoes;

import org.springframework.stereotype.Component;
import pet.palace.api.domain.ValidacaoException;
import pet.palace.api.domain.consulta.DadosAgendarConsulta;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidarHorarioDeAntecedenciaParaMarcarCosnulta implements ValidadorAgendamentoDeConsultas {
    public void validar(DadosAgendarConsulta dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();
        if(diferencaEmMinutos < 60) {
            throw new ValidacaoException("A consulta deve ser marcada com 1 hora ou mais de antecedência");
        }
    }
}
