package pet.palace.api.pet;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pet.palace.api.address.DadosEndereco;
import pet.palace.api.address.Endereco;


@Table(name = "pets")
@Entity(name = "Pet")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String tutor;
    @Enumerated(EnumType.STRING)
    private TipoAnimal tipo;
    private String raca;
    private String condicaoEspecial;
    @Embedded
    private Endereco endereco;

    public Pet(DadosRegistroPet dadosPet) {
        this.nome = dadosPet.nome();
        this.telefone = dadosPet.telefone();
        this.tipo = dadosPet.tipo();
        this.raca = dadosPet.raca();
        this.condicaoEspecial = dadosPet.condicaoEspecial();
        this.endereco = new Endereco(dadosPet.endereco());
        this.tutor = dadosPet.tutor();
    }
}

