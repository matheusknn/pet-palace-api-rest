package pet.palace.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.palace.api.pet.DadosRegistroPet;
import pet.palace.api.pet.Pet;
import pet.palace.api.pet.PetRepository;

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
}
