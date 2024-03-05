package pet.palace.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.palace.api.pet.DadosRegistroPet;

@RestController
@RequestMapping("pets")
public class PetController {
    @PostMapping
    public void registrarPet(@RequestBody DadosRegistroPet dadosPet) {
        System.out.println(dadosPet);
    }
}
