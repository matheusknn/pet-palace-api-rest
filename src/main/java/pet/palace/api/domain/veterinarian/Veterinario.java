package pet.palace.api.domain.veterinarian;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pet.palace.api.domain.address.Endereco;

@Entity(name = "veterinario")
@Table(name = "veterinarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Veterinario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private Boolean ativo;
    @Enumerated(EnumType.STRING)
    private EspecialidadesVeterinario especialidade;
    @Embedded
    private Endereco endereco;

    public Veterinario(DadosRegistroVeterinario dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarDados(DadosAtualizacaoVeterinario dados) {
        if(dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if(dados.endereco() != null) {
            this.endereco.atualizarDadosEndereco(dados.endereco());
        }
    }

    public void inativarVeterinario() {
        this.ativo = false;
    }
}
