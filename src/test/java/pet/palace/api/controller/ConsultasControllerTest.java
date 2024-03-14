package pet.palace.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pet.palace.api.domain.consulta.AgendaDeConsultas;
import pet.palace.api.domain.consulta.Consulta;
import pet.palace.api.domain.consulta.DadosAgendarConsulta;
import pet.palace.api.domain.consulta.DadosConsultaDetalhado;
import pet.palace.api.domain.veterinarian.EspecialidadesVeterinario;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultasControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAgendarConsulta> dadosAgendarConsultaJson;

    @Autowired
    private JacksonTester<DadosConsultaDetalhado> dadosConsultaDetalhadoJson;

    @MockBean
    private AgendaDeConsultas agendaDeConsultas;

    @Test
    @DisplayName("deveria devolver codigo http 400 quando informações estiverem invalidas")
    @WithMockUser
    void agendarConsulta1() throws Exception {
        var response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("deveria devolver codigo http 200 quando informações estiverem válidas")
    @WithMockUser
    void agendarConsulta2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = EspecialidadesVeterinario.GATOS;

        var dadosDetalhamento = new DadosConsultaDetalhado(null, 2L, 5L, data);

        when(agendaDeConsultas.agendarConsulta(any())).thenReturn(dadosDetalhamento);
        var response = mvc.perform(
                post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAgendarConsultaJson.write(
                                new DadosAgendarConsulta(2L, 3L,data, especialidade)
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosConsultaDetalhadoJson.write(
            dadosDetalhamento
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

}