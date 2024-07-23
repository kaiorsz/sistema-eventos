package com.example.sistemaeventos.pojo.input;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;

public class UsuarioDTO {

    private String cpf;
    private String nome;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    public String getCpf() {
        return cpf.replaceAll("[^\\d]", "");
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
