package com.example.sistemaeventos.service;

import com.example.sistemaeventos.entity.Evento;
import com.example.sistemaeventos.pojo.input.EventoDTO;
import com.example.sistemaeventos.pojo.output.EventoVO;

import java.util.List;

public interface EventoService {


    List<EventoVO> findAll(Integer page, Integer size, String sortBy, String sortOrder, Boolean disponivel, String nome, String data);

    void criaEvento(EventoDTO eventoDTO);

    void atualizaEvento(EventoDTO eventoDTO);

    Object findById(Integer id);

    void cancel(Integer id);
}
