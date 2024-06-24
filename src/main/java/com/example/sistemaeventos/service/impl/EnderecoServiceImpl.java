package com.example.sistemaeventos.service.impl;

import com.example.sistemaeventos.dao.EnderecoDao;
import com.example.sistemaeventos.dao.EventoDao;
import com.example.sistemaeventos.dao.IngressoDao;
import com.example.sistemaeventos.dao.UsuarioDao;
import com.example.sistemaeventos.entity.Endereco;
import com.example.sistemaeventos.entity.Evento;
import com.example.sistemaeventos.entity.Ingresso;
import com.example.sistemaeventos.entity.Usuario;
import com.example.sistemaeventos.pojo.input.EnderecoDTO;
import com.example.sistemaeventos.pojo.input.VendaDTO;
import com.example.sistemaeventos.service.EnderecoService;
import com.example.sistemaeventos.service.IngressoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public @Service class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private IngressoDao ingressoDao;
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private EventoDao eventoDao;;
    @Autowired
    private EnderecoDao enderecoDao;


    @Override
    public List<Endereco> findAll(Integer page, Integer size, String sortBy, String sortOrder) {
        List<Endereco> enderecos = enderecoDao.encontrarEnderecos(page, size, sortBy, sortOrder);

        return enderecos;
    }

    @Override
    public void criaEndereco(EnderecoDTO enderecoDTO) {
        if (enderecoDTO.getRua() == null || enderecoDTO.getRua().isEmpty()) {
            throw new RuntimeException("Rua não pode ser vazia.");
        }
        if(enderecoDTO.getNumero() <= 0) {
            throw new RuntimeException("Número não pode ser vazio.");
        }
        if(enderecoDTO.getBairro() == null || enderecoDTO.getBairro().isEmpty()) {
            throw new RuntimeException("Bairro não pode ser vazio.");
        }
        if(enderecoDTO.getCidade() == null || enderecoDTO.getCidade().isEmpty()) {
            throw new RuntimeException("Cidade não pode ser vazia.");
        }
        if(enderecoDTO.getEstado() == null || enderecoDTO.getEstado().isEmpty()) {
            throw new RuntimeException("Estado não pode ser vazio.");
        }
        if(enderecoDTO.getCep() == null || enderecoDTO.getCep().isEmpty()) {
            throw new RuntimeException("CEP não pode ser vazio.");
        }
        try {
            Boolean usuarioExiste = usuarioDao.encontraPorId(enderecoDTO.getUsuario()) != null;
            Boolean eventoExiste = eventoDao.encontraPorId(enderecoDTO.getEvento()) != null;
            if(!usuarioExiste && !eventoExiste) {
                throw new RuntimeException("Usuário ou evento não encontrado.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Usuário ou evento não encontrado.");
        }
        Endereco endereco = dtoToEntiy(enderecoDTO);
        enderecoDao.criar(endereco);
    }

    private Endereco dtoToEntiy(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setRua(enderecoDTO.getRua());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setUsuario(enderecoDTO.getUsuario());
        return endereco;
    }


}
