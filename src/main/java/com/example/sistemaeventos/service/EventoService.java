package com.example.sistemaeventos.service;

import com.example.sistemaeventos.entity.Evento;
import com.example.sistemaeventos.entity.Ingresso;
import com.example.sistemaeventos.pojo.input.EventoDTO;
import com.example.sistemaeventos.pojo.input.VendaDTO;

import java.util.List;

public interface EventoService {


    List<Evento> findAll(Integer page, Integer size, String sortBy, String sortOrder, Boolean disponivel);

    void criaEvento(EventoDTO eventoDTO);
}
