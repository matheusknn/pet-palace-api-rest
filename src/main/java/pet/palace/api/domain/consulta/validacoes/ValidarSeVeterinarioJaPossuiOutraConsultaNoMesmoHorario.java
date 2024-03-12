package pet.palace.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pet.palace.api.domain.ValidacaoException;
import pet.palace.api.domain.consulta.ConsultaRepository;
import pet.palace.api.domain.consulta.DadosAgendarConsulta;

@Component
public class ValidarSeVeterinarioJaPossuiOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsultas {
    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendarConsulta dados) {
        var possuiConsulta = repository.existsByVeterinarioIdAndData(dados.idVeterinario(), dados.data());
        if(possuiConsulta) {
            throw new ValidacaoException("veterinário já possui uma consulta marcada nesse horário");
        }
    }
}
