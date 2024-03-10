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
import pet.palace.api.domain.pet.*;

@RestController
@RequestMapping("pets")
public class PetController {
    @Autowired
    private PetRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity registrarPet(@RequestBody @Valid DadosRegistroPet dadosPet, UriComponentsBuilder uriBuilder) {
        var pet = new Pet(dadosPet);
        repository.save(pet);
        var uri = uriBuilder.path("/pets/{id}").buildAndExpand(pet.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosPetDetalhado(pet));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListarPet>> listarPets(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page =  repository.findAll(paginacao).map(DadosListarPet::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarDadosPet(@RequestBody @Valid DadosAtualizarPet dados) {
        var pet = repository.getReferenceById(dados.id());
        pet.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosPetDetalhado(pet));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirPet(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharPet(@PathVariable Long id) {
        var pet = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosPetDetalhado(pet));
    }

}
