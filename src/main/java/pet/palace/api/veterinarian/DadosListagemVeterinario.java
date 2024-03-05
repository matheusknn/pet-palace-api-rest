package pet.palace.api.veterinarian;

public record DadosListagemVeterinario(String nome, String email, EspecialidadesVeterinario especialidade) {
    public DadosListagemVeterinario(Veterinario veterinario) {
        this(veterinario.getNome(), veterinario.getEmail(), veterinario.getEspecialidade());
    }
}
