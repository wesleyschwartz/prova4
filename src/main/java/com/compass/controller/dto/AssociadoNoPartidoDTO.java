package com.compass.controller.dto;

import com.compass.model.Associado;
import com.compass.model.Partido;
import com.compass.model.enums.Cargo;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AssociadoNoPartidoDTO {
    private String nome;
    private Cargo cargo;
    private String nomePartido;


    public AssociadoNoPartidoDTO(Associado associado, Partido partido) {
        this.setNome(associado.getNome());
        this.setCargo(associado.getCargo());
        this.setNomePartido(partido.getNomePartido());
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public String getNomePartido() {
        return nomePartido;
    }

    public void setNomePartido(String nomePartido) {
        this.nomePartido = nomePartido;
    }
}
