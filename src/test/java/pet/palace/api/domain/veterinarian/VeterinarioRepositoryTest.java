package pet.palace.api.domain.veterinarian;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import pet.palace.api.domain.address.DadosEndereco;
import pet.palace.api.domain.consulta.Consulta;
import pet.palace.api.domain.pet.DadosRegistroPet;
import pet.palace.api.domain.pet.Pet;
import pet.palace.api.domain.pet.TipoAnimal;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class VeterinarioRepositoryTest {

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("deveria devolver null quando veterinario não está disponível na data")
    void escolherVeterinarioAleatorioLivreNaData1() {
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var veterinario = cadastrarVeterinario("Veterinario", "veterinario@pet.palace", EspecialidadesVeterinario.GATOS);
        var pet = cadastrarPet("Pet", TipoAnimal.GATO, "Tutor", "persa");
        cadastrarConsulta(veterinario, pet, proximaSegundaAs10);
        var veterinarioLivre = veterinarioRepository.escolherVeterinarioAleatorioLivreNaData(EspecialidadesVeterinario.GATOS, proximaSegundaAs10);

        assertThat(veterinarioLivre).isNull();
    }

    @Test
    @DisplayName("deveria devolver veterinario quando ele estiver disponível na data")
    void escolherVeterinarioAleatorioLivreNaData2() {
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var veterinario = cadastrarVeterinario("Veterinario", "veterinario@pet.palace", EspecialidadesVeterinario.GATOS);
        var veterinarioLivre = veterinarioRepository.escolherVeterinarioAleatorioLivreNaData(EspecialidadesVeterinario.GATOS, proximaSegundaAs10);

        assertThat(veterinarioLivre).isEqualTo(veterinario);
    }
    private void cadastrarConsulta(Veterinario veterinario, Pet pet, LocalDateTime data) {
        em.persist(new Consulta(null, veterinario, pet, data));
    }

    private Veterinario cadastrarVeterinario(String nome, String email, EspecialidadesVeterinario especialidade) {
        var medico = new Veterinario(dadosVeterinario(nome, email, especialidade));
        em.persist(medico);
        return medico;
    }

    private Pet cadastrarPet(String nome, TipoAnimal tipo, String tutor, String raca) {
        var paciente = new Pet(dadosPet(nome, tipo, tutor, raca));
        em.persist(paciente);
        return paciente;
    }

    private DadosRegistroVeterinario dadosVeterinario(String nome, String email, EspecialidadesVeterinario especialidade) {
        return new DadosRegistroVeterinario(
                nome,
                email,
                "61999999999",
                especialidade,
                dadosEndereco()
        );
    }

    private DadosRegistroPet dadosPet(String nome, TipoAnimal tipo, String tutor, String raca) {
        return new DadosRegistroPet(
                nome,
                tipo,
                "61999999999",
                tutor,
                raca,
                null,
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}