package com.compass.controller.service;

import com.compass.controller.dto.AssociadoNoPartidoDTO;
import com.compass.model.Associado;
import com.compass.model.Partido;
import com.compass.model.enums.Ideologia;
import com.compass.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PartidoService {

    @Autowired
    private PartidoRepository partidoRepository;

    public List<Partido> findByIdeologia(String ideologia) {
        Ideologia ideologiaEnum = Enum.valueOf(Ideologia.class, ideologia.toUpperCase());
        return partidoRepository.findByIdeologia(ideologiaEnum);
    }

    public List<Partido> findAll() {
        return partidoRepository.findAll();
    }

    public Optional<Partido> findPartidoById(Long id) {
        return partidoRepository.findById(id);
    }

    public Partido savePartido(Partido partido) {
        return partidoRepository.save(partido);

    }

    public Partido updatePartido(Long id, Partido partido) {
        Optional<Partido> byId = findPartidoById(id);
        Partido partidoUpdate = byId.get();

        partidoUpdate.setNomePartido(partido.getNomePartido());
        partidoUpdate.setDataDeFundacao(partido.getDataDeFundacao());
        partidoUpdate.setIdeologia(partido.getIdeologia());
        partidoUpdate.setSigla(partido.getSigla());
        partidoUpdate.setDataDeFundacao(partido.getDataDeFundacao());

        return partidoUpdate;
    }

    public void deletePartidoById(Long id) {
        partidoRepository.deleteById(id);
    }

    public List<AssociadoNoPartidoDTO> findLinkedAssociados(Long id) {
        Optional<Partido> byIdPartido = partidoRepository.findById(id);
        Partido partido = byIdPartido.get();
        List<Associado> associados = partido.getAssociadoList();
        List<AssociadoNoPartidoDTO> associadosDTO = associados.stream().map(a -> a.converter(a, partido))
                .collect(Collectors.toList());
        return associadosDTO;
    }
}
