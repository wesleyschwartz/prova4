package com.compass.controller.dto;

import javax.validation.constraints.NotNull;

public class AssociarPartidoDTO {
 @NotNull
    private Long idAssociado;
 @NotNull
 private Long idPartido;

    public AssociarPartidoDTO(Long idAssociado, Long idPartido) {
        this.idAssociado = idAssociado;
        this.idPartido = idPartido;
    }

    public Long getIdAssociado() {
        return idAssociado;
    }

    public Long getIdPartido() {
        return idPartido;
    }
}
