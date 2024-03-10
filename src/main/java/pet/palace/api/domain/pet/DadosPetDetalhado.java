package pet.palace.api.domain.pet;

import pet.palace.api.domain.address.Endereco;

public record DadosPetDetalhado(Long id, String nome, String telefone, String tutor, TipoAnimal tipo, String raca, String condicaoEspecial, Endereco endereco) {
    public DadosPetDetalhado(Pet pet) {
        this(pet.getId(), pet.getNome(), pet.getTelefone(), pet.getTutor(),pet.getTipo(), pet.getRaca(), pet.getCondicaoEspecial(), pet.getEndereco());
    }
}
