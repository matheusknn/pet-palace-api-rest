package pet.palace.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pet.palace.api.domain.ValidacaoException;
import pet.palace.api.domain.consulta.DadosAgendarConsulta;
import pet.palace.api.domain.veterinarian.VeterinarioRepository;

@Component
public class ValidarSeVeterinarioEstaAtivo implements ValidadorAgendamentoDeConsultas {
    @Autowired
    private VeterinarioRepository repository;

    public void validar(DadosAgendarConsulta dados) {
        if(dados.idVeterinario() == null) {
            return;
        }

        var veterinarioEstaAtivo = repository.findAtivoById(dados.idVeterinario());
        if(!veterinarioEstaAtivo) {
            throw new ValidacaoException("o médico não está ativo no momênto");
        }
    }
}
