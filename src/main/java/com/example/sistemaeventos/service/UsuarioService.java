package com.example.sistemaeventos.service;

import com.example.sistemaeventos.entity.Ingresso;
import com.example.sistemaeventos.entity.Usuario;
import com.example.sistemaeventos.pojo.input.UsuarioDTO;
import com.example.sistemaeventos.pojo.input.VendaDTO;

import java.util.List;

public interface UsuarioService {
    Usuario encontraPorId(Integer id);
    List<Usuario> encontraPorCpf(String cpf);

    List<Usuario> findAll(int page, int size, String sortBy, String sortOrder);

    void criaUsuario(UsuarioDTO usuario);
}
