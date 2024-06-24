package com.example.sistemaeventos.service.impl;

import com.example.sistemaeventos.dao.EventoDao;
import com.example.sistemaeventos.dao.IngressoDao;
import com.example.sistemaeventos.dao.UsuarioDao;
import com.example.sistemaeventos.entity.Evento;
import com.example.sistemaeventos.entity.Ingresso;
import com.example.sistemaeventos.entity.Usuario;
import com.example.sistemaeventos.pojo.input.EventoDTO;
import com.example.sistemaeventos.pojo.input.VendaDTO;
import com.example.sistemaeventos.service.EventoService;
import com.example.sistemaeventos.service.IngressoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public @Service class EventoServiceImpl implements EventoService {

    @Autowired
    private IngressoDao ingressoDao;
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private EventoDao eventoDao;

    @Override
    public List<Evento> findAll(Integer page, Integer size, String sortBy, String sortOrder, Boolean disponivel) {
        List<Evento> eventos = eventoDao.encontrarTodos(page, size, sortBy, sortOrder, disponivel);

        return eventos;
    }

    @Override
    public void criaEvento(EventoDTO eventoDTO) {
        if (eventoDTO.getEvento() == null || eventoDTO.getEvento().isEmpty()) {
            throw new RuntimeException("Nome não pode ser vazio.");
        }
        if(eventoDTO.getDataInicial() == null) {
            throw new RuntimeException("Data inicial não pode ser vazia.");
        }
        if(eventoDTO.getDataFinal() == null) {
            throw new RuntimeException("Data final não pode ser vazia.");
        }
        if(eventoDTO.getQuantidadeDisponivel() <= 0) {
            throw new RuntimeException("Quantidade disponível não pode ser vazia.");
        }

        Evento evento = dtoToEntiy(eventoDTO);
        eventoDao.criar(evento);
    }

    private Evento dtoToEntiy(EventoDTO eventoDTO) {
        Evento evento = new Evento();
        evento.setEvento(eventoDTO.getEvento());
        evento.setDataInicial(eventoDTO.getDataInicial());
        evento.setDataFinal(eventoDTO.getDataFinal());
        evento.setQuantidadeDisponivel(eventoDTO.getQuantidadeDisponivel());
        evento.setValorIngresso(eventoDTO.getValorIngresso());
        return evento;
    }
}
