package com.example.sistemaeventos.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class ConexaoJDBC {
    @Value("${projeto.bd.url}")
    private String url;

    @Value("${projeto.bd.username}")
    private String usuario;

    @Value("${projeto.bd.password}")
    private String senha;

    @Value("${projeto.bd.driver}")
    private String driverClassName;

    private static JdbcTemplate jdbcTemplate;

    public synchronized JdbcTemplate getJdbcTemplate() {
        if (jdbcTemplate == null) {
            inicializarJdbcTemplate();
        }
        return jdbcTemplate;
    }

    private void inicializarJdbcTemplate() {
        DataSource dataSource = criarDataSource();
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private DataSource criarDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(usuario);
        dataSource.setPassword(senha);
        return dataSource;
    }
}
