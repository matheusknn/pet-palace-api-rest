package pet.palace.api.veterinarian;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import pet.palace.api.address.DadosEndereco;

public record DadosAtualizacaoVeterinario(
        @NotNull
        Long id,
        String telefone,
        @Valid
        DadosEndereco endereco) {
}
