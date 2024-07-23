package com.example.sistemaeventos.controller;

import com.example.sistemaeventos.pojo.input.UsuarioDTO;
import com.example.sistemaeventos.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @Operation(summary = "Listar todos os usuarios")
    @GetMapping
    public ResponseEntity<Object> findAll(@RequestParam(defaultValue = "0", required = false) int page,
                                          @RequestParam(defaultValue = "10", required = false) int size,
                                          @RequestParam(defaultValue = "id", required = false) String sortBy,
                                          @RequestParam(defaultValue = "asc", required = false) String sortOrder,
                                          @RequestParam(required = false) String nome) {
        try {
            return ResponseEntity.ok(usuarioService.findAll(page, size, sortBy, sortOrder, nome));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Criar um usuario")
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody UsuarioDTO usuario) {
        try {
            usuarioService.criaUsuario(usuario);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            if(e.getMessage().contains("usuario_cpf_key")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF já cadastrado.");
            } else if(e.getMessage().contains("usuario_email_key")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email já cadastrado.");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Atualizar um usuario")
    @PostMapping("/update")
    public ResponseEntity<Object> update(@RequestBody UsuarioDTO usuario,
                                         @RequestParam Integer id) {
        try {
            usuarioService.atualizaUsuario(usuario, id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            if(e.getMessage().contains("usuario_cpf_key")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF já cadastrado.");
            } else if(e.getMessage().contains("usuario_email_key")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email já cadastrado.");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
