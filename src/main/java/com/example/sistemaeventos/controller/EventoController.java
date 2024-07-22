package com.example.sistemaeventos.controller;

import com.example.sistemaeventos.pojo.input.EventoDTO;
import com.example.sistemaeventos.service.EventoService;
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
@RequestMapping("/eventos")
public class EventoController {
    @Autowired
    EventoService eventoService;

    @Operation(summary = "Listar todos os eventos")
    @GetMapping
    public ResponseEntity<Object> findAll(@RequestParam(defaultValue = "0", required = false) int page,
                                          @RequestParam(defaultValue = "10", required = false) int size,
                                          @RequestParam(defaultValue = "id", required = false) String sortBy,
                                          @RequestParam(defaultValue = "asc", required = false) String sortOrder,
                                          @RequestParam(required = false, defaultValue = "false") boolean disponivel,
                                          @RequestParam (required = false) String nome) {
        try {
            return ResponseEntity.ok(eventoService.findAll(page, size, sortBy, sortOrder, disponivel, nome));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Encontrar um evento por id")
    @GetMapping("/find")
    public ResponseEntity<Object> findById(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok(eventoService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Criar um evento")
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody EventoDTO eventoDTO) {
        try {
            eventoService.criaEvento(eventoDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Atualizar um evento")
    @PostMapping("/update")
    public ResponseEntity<Object> update(@RequestBody EventoDTO eventoDTO) {
        try {
            eventoService.atualizaEvento(eventoDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Cancelar um evento")
    @PostMapping("/cancel")
    public ResponseEntity<Object> cancel(@RequestParam Integer id) {
        try {
            eventoService.cancel(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
