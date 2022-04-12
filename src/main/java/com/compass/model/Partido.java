package com.compass.model;

import com.compass.controller.dto.PartidoDTO;
import com.compass.model.enums.Ideologia;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Partido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull @NotEmpty
    private String nomePartido;
    @NotNull @NotEmpty
    private String sigla;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Ideologia ideologia;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeFundacao;

    @OneToMany(orphanRemoval = false)
    @JoinColumn(name = "idPartido")
    private List<Associado> associadoList = new ArrayList<>();

    public Partido() {
    }

    public Partido(String nomePartido, String sigla, Ideologia ideologia, LocalDate dataDeFundacao, List<Associado> associadoList) {
        this.nomePartido = nomePartido;
        this.sigla = sigla;
        this.ideologia = ideologia;
        this.dataDeFundacao = dataDeFundacao;
        this.associadoList = associadoList;
    }

    public Partido(PartidoDTO partidoDTO) {
        this.id = partidoDTO.getId();
        this.nomePartido = partidoDTO.getNomePartido();
        this.sigla = partidoDTO.getSigla();
        this.ideologia = partidoDTO.getIdeologia();
        this.dataDeFundacao = partidoDTO.getDataDeFundacao();
    }

    public Partido(String nomePartido, String sigla, Ideologia ideologia, LocalDate dataDeFundacao) {
        this.nomePartido = nomePartido;
        this.sigla = sigla;
        this.ideologia = ideologia;
        this.dataDeFundacao = dataDeFundacao;
    }

    public Partido(long id, String nomePartido, String sigla, Ideologia ideologia, LocalDate dataDeFundacaot) {
        this.id = id;
        this.nomePartido = nomePartido;
        this.sigla = sigla;
        this.ideologia = ideologia;
        this.dataDeFundacao = dataDeFundacao;
    }

    public List<Associado> getAssociadoList() {
        return associadoList;
    }

    public void addAssociado(Associado associado){
        associadoList.add(associado);
    }
    public long getId() {
        return id;
    }

    public String getNomePartido() {
        return nomePartido;
    }

    public String getSigla() {
        return sigla;
    }

    public Ideologia getIdeologia() {
        return ideologia;
    }

    public LocalDate getDataDeFundacao() {
        return dataDeFundacao;
    }

    public void setNomePartido(String nomePartido) {
        this.nomePartido = nomePartido;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setIdeologia(Ideologia ideologia) {
        this.ideologia = ideologia;
    }

    public void setDataDeFundacao(LocalDate dataDeFundacao) {
        this.dataDeFundacao = dataDeFundacao;
    }

}
