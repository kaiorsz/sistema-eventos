package com.example.sistemaeventos.pojo.output;

import com.example.sistemaeventos.entity.Evento;
import com.example.sistemaeventos.entity.Usuario;
import com.example.sistemaeventos.enums.StatusEventoEnum;

import java.time.LocalDate;

public class EventoVO {
    private Integer id;
    private String evento;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private Double valorIngresso;
    private Integer quantidadeDisponivel;
    private StatusEventoEnum status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Double getValorIngresso() {
        return valorIngresso;
    }

    public void setValorIngresso(Double valorIngresso) {
        this.valorIngresso = valorIngresso;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public StatusEventoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEventoEnum status) {
        this.status = status;
    }
}
