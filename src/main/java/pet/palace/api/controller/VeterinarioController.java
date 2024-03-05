package pet.palace.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pet.palace.api.veterinarian.*;

import java.util.List;

@RestController
@RequestMapping("veterinarios")
public class VeterinarioController {

    @Autowired
    private VeterinarioRepository repository;
    @PostMapping
    @Transactional
    public void registrarVeterinario(@RequestBody @Valid DadosRegistroVeterinario dados) {
        repository.save(new Veterinario(dados));
    }

    @GetMapping
    public Page<DadosListagemVeterinario> listarVeterinarios(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemVeterinario::new);
    }

    @PutMapping
    @Transactional
    public void atualizarDados(@RequestBody @Valid DadosAtualizacaoVeterinario dados) {
        var veterinario = repository.getReferenceById(dados.id());
        veterinario.atualizarDados(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirVeterinario(@PathVariable Long id) {
        var veterinario = repository.getReferenceById(id);
        veterinario.inativarVeterinario();
    }
}
