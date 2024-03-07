package pet.palace.api.pet;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import pet.palace.api.address.DadosEndereco;

public record DadosAtualizarPet(
        @NotNull
        Long id,
        String nome,
        String telefone,
        String tutor,
        @Valid
        DadosEndereco endereco) {
}
