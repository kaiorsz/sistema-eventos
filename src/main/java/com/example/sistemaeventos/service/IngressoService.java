package com.example.sistemaeventos.service;

import com.example.sistemaeventos.entity.Ingresso;
import com.example.sistemaeventos.pojo.input.VendaDTO;

import java.util.List;

public interface IngressoService {

//    void criaIngresso(IngressoDTO ingressoDTO);

    List<Ingresso> findAll(Integer page, Integer size, String sortBy, String sortOrder, Boolean disponivel);

    void vendeIngresso(VendaDTO vendaDTO);

    Object findById(Integer id);

    Object findByUsuario(Integer id);

    void delete(Integer id);
}
