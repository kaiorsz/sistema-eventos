package com.example.sistemaeventos.service.impl;

import com.example.sistemaeventos.dao.EventoDao;
import com.example.sistemaeventos.dao.IngressoDao;
import com.example.sistemaeventos.dao.UsuarioDao;
import com.example.sistemaeventos.entity.Evento;
import com.example.sistemaeventos.enums.StatusEventoEnum;
import com.example.sistemaeventos.pojo.input.EventoDTO;
import com.example.sistemaeventos.pojo.output.EventoVO;
import com.example.sistemaeventos.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public @Service class EventoServiceImpl implements EventoService {

    @Autowired
    private IngressoDao ingressoDao;
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private EventoDao eventoDao;

    @Override
    public List<EventoVO> findAll(Integer page, Integer size, String sortBy, String sortOrder, Boolean disponivel, String nome, String data) {
        if (data != null && !data.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                sdf.parse(data);
            } catch (Exception e) {
                throw new RuntimeException("Data inválida. Informe a data no formato yyyy-MM-dd.");
            }
        }

        List<Evento> eventos = eventoDao.encontrarTodos(page, size, sortBy, sortOrder, disponivel, nome, data);

        List<EventoVO> eventoVOS = new ArrayList<>();
        for(Evento evento : eventos) {
            EventoVO eventoVO = new EventoVO();
            eventoVO.setId(evento.getId());
            eventoVO.setEvento(evento.getEvento());
            eventoVO.setDataInicial(evento.getDataInicial());
            eventoVO.setDataFinal(evento.getDataFinal());
            eventoVO.setQuantidadeDisponivel(evento.getQuantidadeDisponivel());
            eventoVO.setValorIngresso(evento.getValorIngresso());
            eventoVO.setStatus(StatusEventoEnum.getById(evento.getStatus()));
            eventoVOS.add(eventoVO);
        }

        return eventoVOS;
    }

    @Override
    public void criaEvento(EventoDTO eventoDTO) {
        if (eventoDTO.getEvento() == null || eventoDTO.getEvento().isEmpty()) {
            throw new RuntimeException("Nome não pode ser vazio.");
        }
        if (eventoDTO.getDataInicial() == null) {
            throw new RuntimeException("Data inicial não pode ser vazia.");
        }
        if (eventoDTO.getDataFinal() == null) {
            throw new RuntimeException("Data final não pode ser vazia.");
        }
        if (eventoDTO.getQuantidadeDisponivel() <= 0) {
            throw new RuntimeException("Quantidade disponível não pode ser vazia.");
        }

        Evento evento = dtoToEntiy(eventoDTO);
        eventoDao.criar(evento);
    }

    @Override
    public void atualizaEvento(EventoDTO eventoDTO, Integer id) {
        if (eventoDTO.getEvento() == null || eventoDTO.getEvento().isEmpty()) {
            throw new RuntimeException("Nome não pode ser vazio.");
        }
        if (eventoDTO.getDataInicial() == null) {
            throw new RuntimeException("Data inicial não pode ser vazia.");
        }
        if (eventoDTO.getDataFinal() == null) {
            throw new RuntimeException("Data final não pode ser vazia.");
        }
        if (eventoDTO.getQuantidadeDisponivel() <= 0) {
            throw new RuntimeException("Quantidade disponível não pode ser vazia.");
        }

        Evento evento = dtoToEntiy(eventoDTO);
        eventoDao.atualizar(evento, id);
    }

    @Override
    public Object findById(Integer id) {
        Evento evento = eventoDao.encontraPorId(id, null);
        if (evento == null) {
            throw new RuntimeException("Evento não encontrado.");
        }
        return evento;
    }

    @Override
    public void cancel(Integer id) {
        Evento evento = eventoDao.encontraPorId(id, null);
        if (evento == null) {
            throw new RuntimeException("Evento não encontrado.");
        }
        eventoDao.cancelar(id);

    }

    private Evento dtoToEntiy(EventoDTO eventoDTO) {
        Evento evento = new Evento();
        evento.setEvento(eventoDTO.getEvento());
        evento.setDataInicial(eventoDTO.getDataInicial());
        evento.setDataFinal(eventoDTO.getDataFinal());
        evento.setQuantidadeDisponivel(eventoDTO.getQuantidadeDisponivel());
        evento.setValorIngresso(eventoDTO.getValorIngresso());
        evento.setStatus(1);
        return evento;
    }
}
