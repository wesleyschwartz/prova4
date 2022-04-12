package com.compass.controller.service;

import com.compass.controller.dto.AssociadoNoPartidoDTO;
import com.compass.controller.dto.AssociarPartidoDTO;
import com.compass.model.Associado;
import com.compass.model.Partido;
import com.compass.model.enums.Cargo;
import com.compass.repository.AssociadoRepository;
import com.compass.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssociadoService {
    @Autowired
    private AssociadoRepository associadoRepository;
    @Autowired
    private PartidoRepository partidoRepository;


    public AssociadoService(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }

    public List<Associado> findAll() {

        return associadoRepository.findAll();
    }

    public List<Associado> findByCargo(String cargo) {
        Cargo cargoEnum = Enum.valueOf(Cargo.class, cargo.toUpperCase());
        return associadoRepository.findByCargo(cargoEnum);
    }

    public List<Associado> findAllOrderByNome() {
        return associadoRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
    }

    public Optional<Associado> findAssociadoById(Long id) {
        return associadoRepository.findById(id);
    }

    public Associado saveAssociado(Associado associado) {
        return associadoRepository.save(associado);
    }


    public AssociadoNoPartidoDTO linkAssociadoPartido(AssociarPartidoDTO associarPartidoDTO) {

        Associado associado = associadoRepository.findById(associarPartidoDTO.getIdAssociado()).get();
        Partido partido = partidoRepository.findById(associarPartidoDTO.getIdPartido()).get();

        partido.addAssociado(associado);

        return new AssociadoNoPartidoDTO(associado, partido);
    }

    public Associado updateAssociado(Long id, Associado associado) {
        Optional<Associado> byId = findAssociadoById(id);
        Associado associadoUpdate = byId.get();

        associadoUpdate.setNome(associado.getNome());
        associadoUpdate.setCargo(associado.getCargo());
        associadoUpdate.setDataDeNascimento(associado.getDataDeNascimento());
        associadoUpdate.setSexo(associado.getSexo());

        return associadoUpdate;
    }

    public void deleteAssociado(Long id) {
        associadoRepository.deleteById(id);
    }

    public void unlinkAssociacaoPartido(Long idAssociado, Long idPartido) {
        Optional<Associado> byIdAssociado = associadoRepository.findById(idAssociado);
        Associado associado = byIdAssociado.get();
        Optional<Partido> byIdPartido = partidoRepository.findById(idPartido);
        Partido partido = byIdPartido.get();
        partido.getAssociadoList().remove(associado);
    }
}