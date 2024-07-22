package com.example.sistemaeventos.service;

import com.example.sistemaeventos.entity.Usuario;
import com.example.sistemaeventos.pojo.input.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    Usuario encontraPorId(Integer id);
    List<Usuario> encontraPorCpf(String cpf);

    List<Usuario> findAll(int page, int size, String sortBy, String sortOrder, String nome);

    void criaUsuario(UsuarioDTO usuario);

    void atualizaUsuario(UsuarioDTO usuario, Integer id);
}
