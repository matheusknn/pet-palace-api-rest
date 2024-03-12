package pet.palace.api.domain.veterinarian;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
    Page<Veterinario> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select v from veterinario v
            where 
            v.ativo = true
            and v.especialidade = :especialidade
            and
            v.id not in(
                select c.veterinario.id from Consulta c 
                where
                c.data = :data
            )
            order by rand()
            limit 1
            """)
    Veterinario escolherVeterinarioAleatorioLivreNaData(EspecialidadesVeterinario especialidade, LocalDateTime data);
}
