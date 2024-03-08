package pet.palace.api.domain.veterinarian;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pet.palace.api.domain.address.DadosEndereco;

public record DadosRegistroVeterinario(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotNull
        EspecialidadesVeterinario especialidade,
        @NotNull
        @Valid
        DadosEndereco endereco) {
}
