package pet.palace.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.palace.api.veterinarian.DadosRegistroVeterinario;
import pet.palace.api.veterinarian.Veterinario;
import pet.palace.api.veterinarian.VeterinarioRepository;

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
}
