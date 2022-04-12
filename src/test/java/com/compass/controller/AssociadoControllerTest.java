package com.compass.controller;

import com.compass.controller.dto.AssociadoNoPartidoDTO;
import com.compass.controller.dto.AssociarPartidoDTO;
import com.compass.controller.service.AssociadoService;
import com.compass.controller.service.PartidoService;
import com.compass.model.Associado;
import com.compass.model.Partido;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.compass.util.AssociadoCreator.criarAssociadoParaSalvar;
import static com.compass.util.AssociadoCreator.criarAssociadoValido;
import static com.compass.util.PartidoCreator.criarPartidoParaSalvar;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AssociadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AssociadoService associadoService;
    @Autowired
    private PartidoService partidoService;

    @Test
    void deveSalvarRetornaCreated() throws Exception {
        Associado associado = criarAssociadoParaSalvar();
        mockMvc.perform(post("/associados")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(associado)))
                .andExpect(status().is(201));
    }

    @Test
    void naoDeveSalvarRetornaBadRquest() throws Exception {
        Associado associado = criarAssociadoParaSalvar();
        associado.setCargo(null);
        mockMvc.perform(post("/associados")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(associado)))
                .andExpect(status().is(400));
    }

    @Test
    void deveRetornarOk() throws Exception {
        ResultActions perform = mockMvc.perform(get("/associados").contentType(MediaType.APPLICATION_JSON));
        perform.andExpect(((status().isOk())));
    }

    @Test
    void deveRetornarOkQuandoEncontrarAssociadoComId() throws Exception {
        Associado associado = criarAssociadoParaSalvar();
        Associado associado1 = associadoService.saveAssociado(associado);

        ResultActions perform = mockMvc.perform(get("/associados/{id}", associado1.getId()).contentType(MediaType.APPLICATION_JSON));
        perform.andExpect(((status().isOk())));
    }

    @Test
    void deveRetornarNotFoundAssociadoComIDNaoCadastrado() throws Exception {
        Associado associado = criarAssociadoParaSalvar();
        ResultActions perform = mockMvc.perform(get("/associados/{id}", associado.getId()));
        perform.andExpect(((status().is(404))));
    }

    @Test
    void deveRetornarNoContentQuandoDeletarAssociadoComId() throws Exception {
        Associado associado = criarAssociadoValido();
        Associado associado1 = associadoService.saveAssociado(associado);
        ResultActions perform = mockMvc.perform(delete("/associados/{id}", associado1.getId()));
        perform.andExpect(((status().is(204))));
    }

    @Test
    void deveRetornarNotFoundQuandoDeletarAssociadoNaoCadastrado() throws Exception {
        Associado associado = criarAssociadoParaSalvar();
        ResultActions perform = mockMvc.perform(delete
                ("/associados/{id}", associado.getId()));
        perform.andExpect(((status().is(404))));
    }

    @Test
    void deveAtualizarRetornaOk() throws Exception {
        Associado associado = criarAssociadoParaSalvar();
        Associado associado1 = associadoService.saveAssociado(associado);
        mockMvc.perform(put("/associados/{id}", associado1.getId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(associado)))
                .andExpect(status().isOk());
    }

    @Test
    void naoDeveAtualizarRetornaNotFound() throws Exception {
        Associado associado = criarAssociadoParaSalvar();
        mockMvc.perform(put("/associados/{id}", associado.getId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(associado)))
                .andExpect(status().is(404));
    }

    @Test
    void deveVincularAssociadoAPartidoRetornaOk() throws Exception {
        Associado associado = criarAssociadoParaSalvar();
        Partido partido = criarPartidoParaSalvar();
        associadoService.saveAssociado(associado);
        partidoService.savePartido(partido);
        AssociarPartidoDTO associarPartidoDTO =
                new AssociarPartidoDTO(associado.getId(), partido.getId());

        mockMvc.perform(post("/associados/partidos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(associarPartidoDTO)))
                .andExpect(status().isOk());
    }



}

