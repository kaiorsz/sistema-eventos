package com.example.sistemaeventos.dao;

import com.example.sistemaeventos.entity.Evento;
import com.example.sistemaeventos.entity.Ingresso;
import com.example.sistemaeventos.entity.Usuario;
import com.example.sistemaeventos.jdbc.ConexaoJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Component
public class EventoDao {

    @Autowired
    private ConexaoJDBC conexaoJDBC;

    public Evento encontraPorId(Integer id) {
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM evento WHERE id = ?");
            List<Evento> eventos = conexaoJDBC.getJdbcTemplate().query(sql.toString(), new EventoRowMapper(), id);

            if (eventos.isEmpty()) {
                return null;
            }

            return eventos.get(0);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Evento> encontrarTodos(Integer page, Integer size, String sortBy, String sortOrder, Boolean disponivel) {
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM evento");

            // Adicionando ordenação
            sql.append(" ORDER BY ").append(sortBy).append(" ").append(sortOrder);

            // Adicionando paginação
            int offset = page * size;
            sql.append(" LIMIT ").append(size).append(" OFFSET ").append(offset);

            return conexaoJDBC.getJdbcTemplate().query(sql.toString(), new EventoRowMapper());
        } catch (Exception e) {
            throw e;
        }
    }

    public void criar(Evento evento) {
        try {
            StringBuilder sql = new StringBuilder("INSERT INTO evento (evento, datainicial, datafinal, valoringresso, quantidadedisponivel) VALUES (?, ?, ?, ?, ?)");
            conexaoJDBC.getJdbcTemplate().update(sql.toString(), evento.getEvento(), evento.getDataInicial(), evento.getDataFinal(), evento.getValorIngresso(), evento.getQuantidadeDisponivel());
        } catch (Exception e) {
            throw e;
        }
    }

    private class EventoRowMapper implements RowMapper<Evento> {
        @Override
        public Evento mapRow(ResultSet rs, int rowNum) throws SQLException {
            Evento evento = new Evento();
            evento.setId(rs.getInt("id"));
            evento.setEvento(rs.getString("evento"));
            evento.setDataInicial(rs.getObject("datainicial", LocalDate.class));
            evento.setDataFinal(rs.getObject("datafinal", LocalDate.class));
            evento.setValorIngresso(rs.getDouble("valoringresso"));
            evento.setQuantidadeDisponivel(rs.getInt("quantidadedisponivel"));
            return evento;
        }
    }

}
