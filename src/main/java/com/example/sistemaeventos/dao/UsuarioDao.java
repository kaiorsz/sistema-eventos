package com.example.sistemaeventos.dao;

import com.example.sistemaeventos.entity.Ingresso;
import com.example.sistemaeventos.entity.Usuario;
import com.example.sistemaeventos.jdbc.ConexaoJDBC;
import com.example.sistemaeventos.pojo.input.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UsuarioDao {

    @Autowired
    private ConexaoJDBC conexaoJDBC;

    public Usuario encontraPorId(Integer id) {
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM usuario WHERE id = ?");
            List<Usuario> usuarios = conexaoJDBC.getJdbcTemplate().query(sql.toString(), new UsuarioRowMapper(), id);

            if (usuarios.isEmpty()) {
                return null;
            }

            return usuarios.get(0);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Usuario> encontraPorCpf(String cpf) {
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM usuario WHERE cpf = ?");
            return conexaoJDBC.getJdbcTemplate().query(sql.toString(), new UsuarioRowMapper(), cpf);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Usuario> encontrarUsuarios(int page, int size, String sortBy, String sortOrder, String nome) {
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM usuario");

            // Adicionando filtro
            if (nome != null && !nome.isEmpty()) {
                sql.append(" WHERE upper(nome) LIKE '%").append(nome.toUpperCase()).append("%'");
            }

            // Adicionando ordenação
            sql.append(" ORDER BY ").append(sortBy).append(" ").append(sortOrder);

            // Adicionando paginação
            int offset = page * size;
            sql.append(" LIMIT ").append(size).append(" OFFSET ").append(offset);

            return conexaoJDBC.getJdbcTemplate().query(sql.toString(), new UsuarioRowMapper());
        } catch (Exception e) {
            throw e;
        }
    }

    public void criaUsuario(UsuarioDTO usuario) {
        try {
            StringBuilder sql = new StringBuilder("INSERT INTO usuario (nome, cpf, email, datanascimento) VALUES (?, ?, ?, ?)");
            conexaoJDBC.getJdbcTemplate().update(sql.toString(), usuario.getNome(), usuario.getCpf(), usuario.getEmail(), usuario.getDataNascimento());
        } catch (Exception e) {
            throw e;
        }
    }

    public void atualizaUsuario(UsuarioDTO usuario, Integer id) {
        try {
            StringBuilder sql = new StringBuilder("UPDATE usuario SET nome = ?, cpf = ?, email = ?, datanascimento = ? WHERE id = ?");
            conexaoJDBC.getJdbcTemplate().update(sql.toString(), usuario.getNome(), usuario.getCpf(), usuario.getEmail(), usuario.getDataNascimento(), id);
        } catch (Exception e) {
            throw e;
        }
    }

    private class UsuarioRowMapper implements RowMapper<Usuario> {
        @Override
        public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("id"));
            usuario.setNome(rs.getString("nome"));
            usuario.setCpf(rs.getString("cpf"));
            usuario.setEmail(rs.getString("email"));
            usuario.setDataNascimento(rs.getDate("datanascimento"));
            return usuario;
        }
    }

}
