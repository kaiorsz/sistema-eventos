package com.example.sistemaeventos.service;

import com.example.sistemaeventos.entity.Evento;
import com.example.sistemaeventos.pojo.input.EventoDTO;

import java.util.List;

public interface EventoService {


    List<Evento> findAll(Integer page, Integer size, String sortBy, String sortOrder, Boolean disponivel, String nome);

    void criaEvento(EventoDTO eventoDTO);

    void atualizaEvento(EventoDTO eventoDTO);

    Object findById(Integer id);

    void cancel(Integer id);
}
