package pet.palace.api.pet;

import pet.palace.api.address.DadosEndereco;

public record DadosRegistroPet(String nome, TipoAnimal tipo, String raca, String condicaoEspecial, DadosEndereco endereco, DadosTutor tutor) {
}
