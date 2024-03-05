package pet.palace.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pet.palace.api.veterinarian.DadosListagemVeterinario;
import pet.palace.api.veterinarian.DadosRegistroVeterinario;
import pet.palace.api.veterinarian.Veterinario;
import pet.palace.api.veterinarian.VeterinarioRepository;

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
        return repository.findAll(paginacao).map(DadosListagemVeterinario::new);
    }
}
