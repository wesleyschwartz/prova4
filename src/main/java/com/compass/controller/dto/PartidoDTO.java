package com.compass.controller.dto;

import com.compass.model.enums.Ideologia;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class PartidoDTO {
    private long id;
    @NotNull
    private String nomePartido;
    @NotNull
    private String sigla;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Ideologia ideologia;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeFundacao;

    public PartidoDTO(long id, String nomePartido, String sigla, Ideologia ideologia, LocalDate dataDeFundacao) {
        this.id = id;
        this.nomePartido = nomePartido;
        this.sigla = sigla;
        this.ideologia = ideologia;
        this.dataDeFundacao = dataDeFundacao;
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
}
