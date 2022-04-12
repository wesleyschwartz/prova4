package com.compass.controller;

import com.compass.controller.service.PartidoService;
import com.compass.model.Partido;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import static com.compass.util.PartidoCreator.criarPartidoParaSalvar;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PartidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PartidoService partidoService;

    @Test
    void deveSalvarRetornaCreated() throws Exception {
        Partido partido = criarPartidoParaSalvar();
        mockMvc.perform(post("/partidos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(partido
                        )))
                .andExpect(status().is(201));
    }

    @Test
    void naoDeveSalvarRetornaBadRquest() throws Exception {
        Partido partido = criarPartidoParaSalvar();
        partido.setIdeologia(null);
        mockMvc.perform(post("/partidos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(partido
                        )))
                .andExpect(status().is(400));
    }

    @Test
    void deveRetornarOk() throws Exception {
        ResultActions perform = mockMvc.perform(get("/partidos"));
        perform.andExpect(((status().isOk())));
    }

    @Test
    void deveRetornarOkQuandoEncontrarPartidoComId() throws Exception {
        Partido partido = criarPartidoParaSalvar();
        Partido partido1 = partidoService.savePartido(partido);

        ResultActions perform = mockMvc.perform(get("/partidos/ {id}", partido1.getId()));
        perform.andExpect(((status().isOk())));
    }

    @Test
    void deveRetornarNotFoundPartidoComIDNaoCadastrado() throws Exception {
        Partido partido = criarPartidoParaSalvar();
        ResultActions perform = mockMvc.perform(get("/partidos /{id}", partido.getId()));
        perform.andExpect(((status().is(404))));
    }

    @Test
    void deveRetornarNoContentQuandoDeletarPartidoComId() throws Exception {
        Partido partido = criarPartidoParaSalvar();
        Partido partido1 = partidoService.savePartido(partido);
        ResultActions perform = mockMvc.perform(delete("/partidos/{id}", partido1.getId()));
        perform.andExpect(((status().is(204))));
    }

    @Test
    void deveRetornarNotFoundQuandoDeletarPartidoNaoCadastrado() throws Exception {
        Partido partido = criarPartidoParaSalvar();
        ResultActions perform = mockMvc.perform(delete("/partidos/{id}", partido.getId()));
        perform.andExpect(((status().is(404))));
    }

    @Test
    void deveAtualizarRetornaOk() throws Exception {
        Partido partido = criarPartidoParaSalvar();
        Partido partido1 = partidoService.savePartido(partido);
        mockMvc.perform(put("/partidos/{id}", partido1.getId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(partido
                        )))
                .andExpect(status().isOk());
    }

    @Test
    void naoDeveAtualizarRetornaNotFound() throws Exception {
        Partido partido = criarPartidoParaSalvar();
        mockMvc.perform(put("/partidos/{id}", partido
                                .getId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(partido
                        )))
                .andExpect(status().is(404));
    }


}

