package pet.palace.api.veterinarian;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pet.palace.api.address.Endereco;

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
    @Enumerated(EnumType.STRING)
    private EspecialidadesVeterinario especialidade;
    @Embedded
    private Endereco endereco;

    public Veterinario(DadosRegistroVeterinario dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }
}
