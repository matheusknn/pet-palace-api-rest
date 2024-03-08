package pet.palace.api.domain.veterinarian;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import pet.palace.api.domain.address.DadosEndereco;

public record DadosAtualizacaoVeterinario(
        @NotNull
        Long id,
        String telefone,
        @Valid
        DadosEndereco endereco) {
}
