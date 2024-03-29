package pet.palace.api.domain.veterinarian;

public record DadosListagemVeterinario(Long id, String nome, String email, EspecialidadesVeterinario especialidade) {
    public DadosListagemVeterinario(Veterinario veterinario) {
        this(veterinario.getId(), veterinario.getNome(), veterinario.getEmail(), veterinario.getEspecialidade());
    }
}
