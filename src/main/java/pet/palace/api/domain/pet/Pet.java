package pet.palace.api.domain.pet;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pet.palace.api.domain.address.Endereco;


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

    public void atualizarInformacoes(DadosAtualizarPet dados) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }
        if(dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if(dados.endereco() != null) {
            this.endereco.atualizarDadosEndereco(dados.endereco());
        }
        if(dados.tutor() != null) {
            this.tutor = dados.tutor();
        }
    }
}

