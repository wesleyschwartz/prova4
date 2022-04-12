package com.compass.controller;

import com.compass.controller.dto.AssociadoNoPartidoDTO;
import com.compass.controller.dto.AssociarPartidoDTO;
import com.compass.model.Associado;
import com.compass.controller.service.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/associados")
public class AssociadoController {

    @Autowired
    private AssociadoService associadoService;

    @GetMapping
    //Lista todos associados, lista por cargo '?cargo=?', e lista na ordem alfabética '?nome'
    public ResponseEntity<List<Associado>> findAllOrByCargoOrderByNome(@RequestParam(required = false) String cargo, String nome) {
        if (nome != null) {
            return ResponseEntity.ok(associadoService.findAllOrderByNome());
        }
        if (cargo != null) {
            return ResponseEntity.ok(associadoService.findByCargo(cargo));
        }
        return ResponseEntity.ok(associadoService.findAll());
    }

    @GetMapping("/{id}")
    //busca o associado com o id informado
    public ResponseEntity<Associado> findAssociadoById(@PathVariable Long id) {
        Optional<Associado> optionalAssociado = associadoService.findAssociadoById(id);
        if (optionalAssociado.isPresent()) {
            return ResponseEntity.ok(optionalAssociado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    //cadastra um associado
    public ResponseEntity<Associado> saveAssociado(@RequestBody @Valid Associado associado, UriComponentsBuilder uriBuilder) {
        URI uri = uriBuilder.path("/associados/{id}").buildAndExpand(associado.getId()).toUri();
        return ResponseEntity.created(uri).body(associadoService.saveAssociado(associado));
    }

    @PostMapping("/partidos")
    @Transactional
    //vincula um associado em um partido
    public ResponseEntity<AssociadoNoPartidoDTO> linkAssociadoPartido
            (@RequestBody @Valid AssociarPartidoDTO
                     associarPartidoDTO, UriComponentsBuilder uriBuilder) {
        AssociadoNoPartidoDTO associado = associadoService.linkAssociadoPartido(associarPartidoDTO);

        return ResponseEntity.ok(associado);
    }

    @PutMapping("/{id}")
    @Transactional
    //atualiza o associado informado pelo id
    public ResponseEntity<Associado> updateAssociado(@PathVariable Long id, @RequestBody @Valid Associado associado) {
        Optional<Associado> optionalAssociado = associadoService.findAssociadoById(id);
        if (optionalAssociado.isPresent()) {
            return ResponseEntity.ok(associadoService.updateAssociado(id, associado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    @Transactional
    //deleta o associado informado pelo id
    public ResponseEntity<?> deleteAssociado(@PathVariable Long id) {
        Optional<Associado> optionalAssociado = associadoService.findAssociadoById(id);
        if (optionalAssociado.isPresent()) {
            associadoService.deleteAssociado(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idAssociado}/partidos/{idPartido}")
    @Transactional
    //desvincula o associado do partido
    public ResponseEntity<?> unlinkAssociacaoPartido(@PathVariable Long idAssociado, @PathVariable Long idPartido) {
        associadoService.unlinkAssociacaoPartido(idAssociado, idPartido);
        return ResponseEntity.noContent().build();
    }

}
