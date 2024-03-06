package pet.palace.api.pet;

public record DadosListarPet(
        Long id,
        String nome,
        String tutor,
        String telefone,
        TipoAnimal tipo,
        String raca) {

        public DadosListarPet(Pet pet) {
            this(pet.getId(),pet.getNome(), pet.getTutor(), pet.getTelefone(), pet.getTipo(), pet.getRaca());
        }
}
