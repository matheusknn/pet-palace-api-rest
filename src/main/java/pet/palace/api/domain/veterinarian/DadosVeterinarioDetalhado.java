package pet.palace.api.domain.veterinarian;

import pet.palace.api.domain.address.Endereco;

public record DadosVeterinarioDetalhado(Long id, String nome, String email, String telefone, EspecialidadesVeterinario espscialidade, Endereco endereco) {
    public DadosVeterinarioDetalhado(Veterinario veterinario) {
        this(veterinario.getId(), veterinario.getNome(), veterinario.getEmail(), veterinario.getTelefone(), veterinario.getEspecialidade(), veterinario.getEndereco());
    }
}
