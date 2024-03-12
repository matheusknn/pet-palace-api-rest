package pet.palace.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.palace.api.domain.consulta.AgendaDeConsultas;
import pet.palace.api.domain.consulta.DadosAgendarConsulta;
import pet.palace.api.domain.consulta.DadosConsultaDetalhado;

@RestController
@RequestMapping("consultas")
public class ConsultasController {

    @Autowired
    private AgendaDeConsultas agenda;
    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(@RequestBody @Valid DadosAgendarConsulta dados) {
        var dto = agenda.agendarConsulta(dados);
        return ResponseEntity.ok(dto);
    }
}
