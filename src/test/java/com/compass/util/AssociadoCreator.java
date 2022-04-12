package com.compass.util;

import com.compass.model.Associado;
import com.compass.model.enums.Cargo;
import com.compass.model.enums.Sexo;

import java.time.LocalDate;

public class AssociadoCreator {

    public static Associado criarAssociadoParaSalvar() {
        return new Associado("Teste", Cargo.VEREADOR, LocalDate.now(), Sexo.MASCULINO);
    }
    public static Associado criarAssociadoValido() {
        long id= 1;
        return new Associado(id,"Teste", Cargo.VEREADOR, LocalDate.now(), Sexo.MASCULINO);
    }



}
