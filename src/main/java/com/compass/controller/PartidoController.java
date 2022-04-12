package com.compass.controller;

import com.compass.controller.dto.AssociadoNoPartidoDTO;
import com.compass.controller.service.PartidoService;
import com.compass.model.Partido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/partidos")
public class PartidoController {

    @Autowired
    private PartidoService partidoService;


    @GetMapping
    //busca todos os partidos || ordena por ideologia ?ideologia=?centro||direita||esquerda
    public ResponseEntity<List<Partido>> findAllOrFindByIdeologia(@RequestParam(required = false) String ideologia) {
        if (ideologia != null) {
            return ResponseEntity.ok(partidoService.findByIdeologia(ideologia));
        }
        return ResponseEntity.ok(partidoService.findAll());
    }

    @GetMapping("/{id}")
    //busca o partido com o id informado
    public ResponseEntity<Partido> findPartidoById(@PathVariable Long id) {
        Optional<Partido> optionalPartido = partidoService.findPartidoById(id);
        if (optionalPartido.isPresent()) {
            return ResponseEntity.ok(optionalPartido.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/associados")
    //busca associados vinculados no partido informado
    public ResponseEntity<List<AssociadoNoPartidoDTO>> findLinkedAssociados(@PathVariable Long id) {
        return ResponseEntity.ok(partidoService.findLinkedAssociados(id));
    }

    @PostMapping
    @Transactional
    //salva um partido
    public ResponseEntity<Partido> savePartido
            (@RequestBody @Valid Partido
                     partido, UriComponentsBuilder uriBuilder) {
        URI uri = uriBuilder.path("/partidos/{id}").buildAndExpand(partido.getId()).toUri();
        return ResponseEntity.created(uri).body(partidoService.savePartido(partido));
    }

    @PutMapping("/{id}")
    @Transactional
    //atualiza o partido informado
    public ResponseEntity<Partido> updatePartido
            (@RequestBody @Valid Partido
                     partido, @PathVariable Long id) {
        Optional<Partido> optionalPartido = partidoService.findPartidoById(id);
        if (optionalPartido.isPresent()) {
            return ResponseEntity.ok(partidoService.updatePartido(id, partido));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    @Transactional
    //deleta o partido informado
    public ResponseEntity<?> deletePartidoById(@PathVariable Long id) {
        Optional<Partido> optionalPartido = partidoService.findPartidoById(id);
        if (optionalPartido.isPresent()) {
           partidoService.deletePartidoById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }


    }
}
