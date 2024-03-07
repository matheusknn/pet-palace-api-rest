package pet.palace.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pet.palace.api.pet.*;

import java.util.List;

@RestController
@RequestMapping("pets")
public class PetController {
    @Autowired
    private PetRepository repository;
    @PostMapping
    @Transactional
    public void registrarPet(@RequestBody @Valid  DadosRegistroPet dadosPet) {
        repository.save(new Pet(dadosPet));
    }

    @GetMapping
    public Page<DadosListarPet> listarPets(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListarPet::new);
    }

    @PutMapping
    @Transactional
    public void atualizarDadosPet(@RequestBody @Valid DadosAtualizarPet dados) {
        var pet = repository.getReferenceById(dados.id());
        pet.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirPet(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
