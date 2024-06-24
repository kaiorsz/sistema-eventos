package com.example.sistemaeventos.dao;

import com.example.sistemaeventos.entity.Endereco;
import com.example.sistemaeventos.entity.Evento;
import com.example.sistemaeventos.entity.Usuario;
import com.example.sistemaeventos.jdbc.ConexaoJDBC;
import com.example.sistemaeventos.pojo.input.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Component
public class EnderecoDao {

    @Autowired
    private ConexaoJDBC conexaoJDBC;

    public void criar(Endereco endereco) {
        try {
            StringBuilder sql = new StringBuilder("INSERT INTO endereco (rua, numero, bairro, cidade, estado, cep, usuario, evento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            conexaoJDBC.getJdbcTemplate().update(sql.toString(), endereco.getRua(), endereco.getNumero(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado(), endereco.getCep(), endereco.getUsuario(), endereco.getEvento());
        } catch (Exception e) {
            throw e;
        }
    }

    private class EnderecoRowMapper implements RowMapper<Endereco> {
        @Override
        public Endereco mapRow(ResultSet rs, int rowNum) throws SQLException {
            Endereco endereco = new Endereco();
            endereco.setId(rs.getInt("id"));
            endereco.setRua(rs.getString("rua"));
            endereco.setNumero(rs.getInt("numero"));
            endereco.setBairro(rs.getString("bairro"));
            endereco.setCidade(rs.getString("cidade"));
            endereco.setEstado(rs.getString("estado"));
            endereco.setCep(rs.getString("cep"));
            endereco.setUsuario(rs.getInt("usuario"));
            endereco.setEvento(rs.getInt("evento"));
            return endereco;
        }
    }

    public List<Endereco> encontrarEnderecos(Integer page, Integer size, String sortBy, String sortOrder) {
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM endereco");

            // Adicionando ordenação
            sql.append(" ORDER BY ").append(sortBy).append(" ").append(sortOrder);

            // Adicionando paginação
            int offset = page * size;
            sql.append(" LIMIT ").append(size).append(" OFFSET ").append(offset);

            return conexaoJDBC.getJdbcTemplate().query(sql.toString(), new EnderecoRowMapper());
        } catch (Exception e) {
            throw e;
        }
    }

}
