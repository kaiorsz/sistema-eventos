package com.example.sistemaeventos.controller;

import com.example.sistemaeventos.pojo.input.VendaDTO;
import com.example.sistemaeventos.service.IngressoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ingressos")
public class IngressoController {
    @Autowired
    IngressoService ingressoService;

    @Operation(summary = "Listar todos os ingressos")
    @GetMapping
    public ResponseEntity<Object> findAll(@RequestParam(defaultValue = "0", required = false) int page,
                                          @RequestParam(defaultValue = "10", required = false) int size,
                                          @RequestParam(defaultValue = "id", required = false) String sortBy,
                                          @RequestParam(defaultValue = "asc", required = false) String sortOrder,
                                          @RequestParam(required = false, defaultValue = "false") boolean disponivel) {
        try {
            return ResponseEntity.ok(ingressoService.findAll(page, size, sortBy, sortOrder, disponivel));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Encontrar um ingresso por id")
    @GetMapping("/find")
    public ResponseEntity<Object> findById(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok(ingressoService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Encontrar um ingresso por usuario")
    @GetMapping("/find/user")
    public ResponseEntity<Object> findByUsuario(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok(ingressoService.findByUsuario(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Vender um ingresso")
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody VendaDTO vendaDTO) {
        try {
            ingressoService.vendeIngresso(vendaDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "Deletar um ingresso (estornar)")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam Integer id) {
        try {
            ingressoService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
