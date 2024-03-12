package pet.palace.api.domain.consulta.validacoes;

import pet.palace.api.domain.consulta.DadosAgendarConsulta;

public interface ValidadorAgendamentoDeConsultas {
    void validar(DadosAgendarConsulta dados);
}
