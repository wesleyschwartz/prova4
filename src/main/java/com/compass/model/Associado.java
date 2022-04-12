package com.compass.model;

import com.compass.controller.dto.AssociadoNoPartidoDTO;
import com.compass.model.enums.Cargo;
import com.compass.model.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Entity
public class Associado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull @NotEmpty
    private String nome;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Cargo cargo;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeNascimento;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    public Associado() {
    }

    public Associado(String nomeAssociado, Cargo cargoPolitico, LocalDate dataDeNascimento, Sexo sexo) {
        this.nome = nomeAssociado;
        this.cargo = cargoPolitico;
        this.dataDeNascimento = dataDeNascimento;
        this.sexo = sexo;
    }

    public Associado(long id, String nome, Cargo cargo, LocalDate dataDeNascimento, Sexo sexo) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.dataDeNascimento = dataDeNascimento;
        this.sexo = sexo;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public AssociadoNoPartidoDTO converter(Associado associado, Partido partido) {
        return new AssociadoNoPartidoDTO(associado, partido);
    }
}
