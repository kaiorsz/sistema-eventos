package com.example.sistemaeventos.pojo.output;

import com.example.sistemaeventos.entity.Evento;
import com.example.sistemaeventos.entity.Usuario;

public class IngressoVO {
    private Integer id;
    private Evento evento;
    private Usuario usuario;

    public Evento getEvento() {
        return evento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
