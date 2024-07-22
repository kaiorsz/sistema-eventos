package com.example.sistemaeventos.enums;

public enum StatusEventoEnum {

    CONFIRMADO(1, "Confirmado"),
    CANCELADO(2, "Cancelado");

    private Integer id;
    private String descricao;

    StatusEventoEnum(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
