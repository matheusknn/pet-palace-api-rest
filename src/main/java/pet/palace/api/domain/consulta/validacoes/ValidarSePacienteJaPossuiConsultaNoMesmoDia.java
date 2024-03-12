package pet.palace.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pet.palace.api.domain.ValidacaoException;
import pet.palace.api.domain.consulta.ConsultaRepository;
import pet.palace.api.domain.consulta.DadosAgendarConsulta;

@Component
public class ValidarSePacienteJaPossuiConsultaNoMesmoDia implements ValidadorAgendamentoDeConsultas{
    @Autowired
    private ConsultaRepository repository;
    public void validar(DadosAgendarConsulta dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(17);
        var pacienteJaPossuiConsulta = repository.existsByPetIdAndDataBetween(dados.idPet(), primeiroHorario, ultimoHorario);
        if(pacienteJaPossuiConsulta) {
            throw new ValidacaoException("Paciente já possui consulta agendda nesse dia");
        }
    }
}
