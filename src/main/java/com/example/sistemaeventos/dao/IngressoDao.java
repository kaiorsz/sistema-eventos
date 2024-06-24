package com.example.sistemaeventos.dao;

import com.example.sistemaeventos.entity.Ingresso;
import com.example.sistemaeventos.jdbc.ConexaoJDBC;
import com.example.sistemaeventos.pojo.input.VendaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class IngressoDao {

    @Autowired
    private ConexaoJDBC conexaoJDBC;

    public void vendeIngresso(VendaDTO vendaDTO) {
        try {
            StringBuilder sql = new StringBuilder("INSERT INTO ingresso (evento, usuario) VALUES (?, ?)");
            conexaoJDBC.getJdbcTemplate().update(sql.toString(), vendaDTO.getEvento(), vendaDTO.getUsuario());

            sql = new StringBuilder("UPDATE evento SET quantidade_disponivel = quantidade_disponivel - 1 WHERE id = ?");
            conexaoJDBC.getJdbcTemplate().update(sql.toString(), vendaDTO.getEvento());
        } catch (Exception e) {
            throw e;
        }
    }

    private class IngressoRowMapper implements RowMapper<Ingresso> {
        @Override
        public Ingresso mapRow(ResultSet rs, int rowNum) throws SQLException {
            Ingresso ingresso = new Ingresso();
            ingresso.setId(rs.getInt("id"));
            ingresso.setEvento(rs.getInt("evento"));
            ingresso.setUsuario(rs.getInt("usuario"));
            return ingresso;
        }
    }
//
//    public void criar(Ingresso ingresso) {
//        try {
//            StringBuilder sql = new StringBuilder("INSERT INTO ingresso (nome, descricao, quantidade_disponivel, valor_unitario) VALUES (?, ?, ?, ?)");
//            conexaoJDBC.getJdbcTemplate().update(sql.toString(), ingresso.getNome(), ingresso.getDescricao(), ingresso.getQuantidade_disponivel(), ingresso.getValor_unitario());
//        } catch (Exception e) {
//            throw e;
//        }
//    }

    public List<Ingresso> encontrarIngressosVendidos(Integer page, Integer size, String sortBy, String sortOrder, Boolean disponivel) {
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM ingresso");

            // Adicionando ordenação
            sql.append(" ORDER BY ").append(sortBy).append(" ").append(sortOrder);

            // Adicionando paginação
            int offset = page * size;
            sql.append(" LIMIT ").append(size).append(" OFFSET ").append(offset);

            return conexaoJDBC.getJdbcTemplate().query(sql.toString(), new IngressoRowMapper());
        } catch (Exception e) {
            throw e;
        }
    }
}
