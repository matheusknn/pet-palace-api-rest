package pet.palace.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pet.palace.api.veterinarian.*;

import java.util.List;

@RestController
@RequestMapping("veterinarios")
public class VeterinarioController {

    @Autowired
    private VeterinarioRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity registrarVeterinario(@RequestBody @Valid DadosRegistroVeterinario dados, UriComponentsBuilder uriBuilder) {
        var medico = new Veterinario(dados);
        repository.save(medico);
        var uri = uriBuilder.path("/veterinarios/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosVeterinarioDetalhado(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemVeterinario>> listarVeterinarios(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemVeterinario::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarDados(@RequestBody @Valid DadosAtualizacaoVeterinario dados) {
        var veterinario = repository.getReferenceById(dados.id());
        veterinario.atualizarDados(dados);

        return ResponseEntity.ok(new DadosVeterinarioDetalhado(veterinario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirVeterinario(@PathVariable Long id) {
        var veterinario = repository.getReferenceById(id);
        veterinario.inativarVeterinario();
        return ResponseEntity.noContent().build();
    }
}
