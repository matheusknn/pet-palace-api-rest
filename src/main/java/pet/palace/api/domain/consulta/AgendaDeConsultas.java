package pet.palace.api.domain.consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.palace.api.domain.ValidacaoException;
import pet.palace.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsultas;
import pet.palace.api.domain.pet.PetRepository;
import pet.palace.api.domain.veterinarian.Veterinario;
import pet.palace.api.domain.veterinarian.VeterinarioRepository;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsultas> validadores;
    public DadosConsultaDetalhado agendarConsulta(DadosAgendarConsulta dados) {
        if(!petRepository.existsById(dados.idPet())) {
            throw new ValidacaoException("id do pet informado não existe");
        }
        if(dados.idVeterinario() != null && !veterinarioRepository.existsById(dados.idVeterinario())) {
            throw new ValidacaoException("id do veterinário informado não existe");
        }
        validadores.forEach(v -> v.validar(dados));
        var pet = petRepository.getReferenceById(dados.idPet());
        var veterinario = escolherVeterinario(dados);
        if(veterinario == null) {
            throw new ValidacaoException("não existe veterinário disponível nessa data");
        }
        var consulta = new Consulta(null, veterinario, pet, dados.data());
        consultaRepository.save(consulta);
        return new DadosConsultaDetalhado(consulta);
    }

    private Veterinario escolherVeterinario(DadosAgendarConsulta dados) {
        if(dados.idVeterinario() != null) {
            return veterinarioRepository.getReferenceById(dados.idVeterinario());
        }
        if(dados.especialidade() == null) {
            throw new ValidacaoException("especialidade é obrigatória quando veterinário não for escolhido");
        }
        return veterinarioRepository.escolherVeterinarioAleatorioLivreNaData(dados.especialidade(), dados.data());
    }
}
