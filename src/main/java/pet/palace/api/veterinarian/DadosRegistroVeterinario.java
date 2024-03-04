package pet.palace.api.veterinarian;

import pet.palace.api.address.DadosEndereco;

public record DadosRegistroVeterinario(String nome, String email, EspecialidadesVeterinario especialidade, DadosEndereco endereco) {
}
