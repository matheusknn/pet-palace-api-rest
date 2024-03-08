package pet.palace.api.domain.pet;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import pet.palace.api.domain.address.DadosEndereco;

public record DadosAtualizarPet(
        @NotNull
        Long id,
        String nome,
        String telefone,
        String tutor,
        @Valid
        DadosEndereco endereco) {
}
