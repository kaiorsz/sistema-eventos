package com.example.sistemaeventos.service.impl;

import com.example.sistemaeventos.dao.UsuarioDao;
import com.example.sistemaeventos.entity.Usuario;
import com.example.sistemaeventos.pojo.input.UsuarioDTO;
import com.example.sistemaeventos.service.UsuarioService;
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
    public List<Usuario> findAll(int page, int size, String sortBy, String sortOrder) {
        return usuarioDao.encontrarUsuarios(page, size, sortBy, sortOrder);
    }

    @Override
    public void criaUsuario(UsuarioDTO usuario) {
        usuarioDao.criaUsuario(usuario);
    }
}
