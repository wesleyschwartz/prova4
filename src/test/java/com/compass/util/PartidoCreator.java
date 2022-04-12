package com.compass.util;

import com.compass.model.Partido;
import com.compass.model.enums.Ideologia;

import java.time.LocalDate;

public class PartidoCreator {
    public static Partido criarPartidoParaSalvar() {
        return new Partido("PArtido do TESTE", "TESTE", Ideologia.CENTRO, LocalDate.now());
    }

}
