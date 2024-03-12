package pet.palace.api.domain.consulta;

import java.time.LocalDateTime;

public record DadosConsultaDetalhado(Long id, Long idVeterinario, Long idPet, LocalDateTime data) {
    public DadosConsultaDetalhado(Consulta consulta) {
        this(consulta.getId(), consulta.getVeterinario().getId(), consulta.getPet().getId(), consulta.getData());
    }
}
