package com.example.sistemaeventos.service;

import com.example.sistemaeventos.entity.Endereco;
import com.example.sistemaeventos.entity.Evento;
import com.example.sistemaeventos.pojo.input.EnderecoDTO;
import com.example.sistemaeventos.pojo.input.EventoDTO;

import java.util.List;

public interface EnderecoService {

    List<Endereco> findAll(Integer page, Integer size, String sortBy, String sortOrder);

    void criaEndereco(EnderecoDTO enderecoDTO);
}
