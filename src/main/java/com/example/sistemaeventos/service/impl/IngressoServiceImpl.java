package com.example.sistemaeventos.service.impl;

import com.example.sistemaeventos.dao.EventoDao;
import com.example.sistemaeventos.dao.IngressoDao;
import com.example.sistemaeventos.dao.UsuarioDao;
import com.example.sistemaeventos.entity.Evento;
import com.example.sistemaeventos.entity.Ingresso;
import com.example.sistemaeventos.entity.Usuario;
import com.example.sistemaeventos.pojo.input.VendaDTO;
import com.example.sistemaeventos.pojo.output.IngressoVO;
import com.example.sistemaeventos.service.IngressoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public @Service class IngressoServiceImpl implements IngressoService {

    @Autowired
    private IngressoDao ingressoDao;
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private EventoDao eventoDao;

    @Override
    public List<Ingresso> findAll(Integer page, Integer size, String sortBy, String sortOrder) {
        List<Ingresso> ingressos = ingressoDao.encontrarIngressosVendidos(page, size, sortBy, sortOrder);

        return ingressos;
    }

    @Override
    public void vendeIngresso(VendaDTO vendaDTO) {
        Usuario usuario = usuarioDao.encontraPorId(vendaDTO.getUsuario());
        if(usuario == null) {
            throw new RuntimeException("Usuário não encontrado.");
        }
        Evento evento = eventoDao.encontraPorId(vendaDTO.getEvento(), true);
        if(evento == null) {
            throw new RuntimeException("Evento não encontrado.");
        }

        if(evento.getQuantidadeDisponivel() <= 0) {
            throw new RuntimeException("Ingressos esgotados.");
        }

        ingressoDao.vendeIngresso(vendaDTO,evento.getValorIngresso());
    }

    @Override
    public Object findById(Integer id) {
        Ingresso ingresso = ingressoDao.encontraPorId(id);
        if(ingresso == null) {
            throw new RuntimeException("Ingresso não encontrado.");
        }

        IngressoVO ingressoVO = new IngressoVO();
        ingressoVO.setId(ingresso.getId());
        ingressoVO.setEvento(eventoDao.encontraPorId(ingresso.getEvento(), null));
        ingressoVO.setUsuario(usuarioDao.encontraPorId(ingresso.getUsuario()));

        return ingressoVO;
    }

    @Override
    public Object findByUsuario(Integer id) {
        Usuario usuario = usuarioDao.encontraPorId(id);
        if(usuario == null) {
            throw new RuntimeException("Usuário não encontrado.");
        }

        List<Ingresso> ingressos = ingressoDao.encontraPorUsuario(id);
        if(ingressos.isEmpty()) {
            throw new RuntimeException("Nenhum ingresso encontrado.");
        }

        List<IngressoVO> ingressoVOS = new ArrayList<>();

        for(Ingresso ingresso : ingressos) {
            Evento evento = eventoDao.encontraPorId(ingresso.getEvento(), null);
            IngressoVO ingressoVO = new IngressoVO();
            ingressoVO.setId(ingresso.getId());
            ingressoVO.setEvento(evento);
            ingressoVO.setUsuario(usuario);
            ingressoVOS.add(ingressoVO);
        }

        return ingressoVOS;
    }

    @Override
    public void delete(Integer id) {
        Ingresso ingresso = ingressoDao.encontraPorId(id);
        if(ingresso == null) {
            throw new RuntimeException("Ingresso não encontrado.");
        }

        ingressoDao.deletar(id, ingresso.getEvento());
    }
}
