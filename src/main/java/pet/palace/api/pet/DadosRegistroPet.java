package pet.palace.api.pet;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pet.palace.api.address.DadosEndereco;

public record DadosRegistroPet(
        @NotBlank
        String nome,
        @NotNull
        TipoAnimal tipo,
        @NotBlank
        String telefone,
        @NotBlank
        String tutor,
        @NotBlank
        String raca,
        String condicaoEspecial,
        @NotNull
        @Valid
        DadosEndereco endereco) {
}
