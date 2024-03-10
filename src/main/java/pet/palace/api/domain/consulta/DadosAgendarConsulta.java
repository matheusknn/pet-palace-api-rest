package pet.palace.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendarConsulta(
        Long idVeterinario,
        @NotNull
        Long idPet,
        @NotNull
        @Future
        LocalDateTime data) {
}
