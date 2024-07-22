package com.example.sistemaeventos.service.impl;

import com.example.sistemaeventos.dao.UsuarioDao;
import com.example.sistemaeventos.entity.Usuario;
import com.example.sistemaeventos.pojo.input.UsuarioDTO;
import com.example.sistemaeventos.service.UsuarioService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public @Service class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public Usuario encontraPorId(Integer id) {
        return usuarioDao.encontraPorId(id);
    }

    @Override
    public List<Usuario> encontraPorCpf(String cpf) {
        return usuarioDao.encontraPorCpf(cpf);
    }

    @Override
    public List<Usuario> findAll(int page, int size, String sortBy, String sortOrder, String nome) {
        return usuarioDao.encontrarUsuarios(page, size, sortBy, sortOrder, nome);
    }

    @Override
    public void criaUsuario(UsuarioDTO usuario) {
        usuarioDao.criaUsuario(usuario);
    }

    @Override
    public void atualizaUsuario(UsuarioDTO usuario, Integer id) {
        try {
           if(usuarioDao.encontraPorId(id) == null) {
               throw new RuntimeException("Usuário não encontrado.");
           }
        } catch (Exception e) {
            throw new ServiceException("Não foi possível encontrar o usuário. Informe um id válido como parâmetro.");
        }

        usuarioDao.atualizaUsuario(usuario, id);
    }
}
