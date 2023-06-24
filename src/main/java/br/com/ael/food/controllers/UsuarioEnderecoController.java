package br.com.ael.food.controllers;

import br.com.ael.food.requests.ClienteWebEnderecoRequestDTO;
import br.com.ael.food.requests.UsuarioEnderecoRequestDTO;
import br.com.ael.food.services.ClienteWebEnderecoService;
import br.com.ael.food.services.UsuarioEnderecoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario-enderecos")
public class UsuarioEnderecoController {

    private UsuarioEnderecoService usuarioEnderecoService;

    public UsuarioEnderecoController(UsuarioEnderecoService usuarioEnderecoService) {
        this.usuarioEnderecoService = usuarioEnderecoService;
    }

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody UsuarioEnderecoRequestDTO usuarioEnderecoRequestDTO) {
        this.usuarioEnderecoService.save(usuarioEnderecoRequestDTO);
        return ResponseEntity.ok("sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPeloId(@PathVariable(name = "id") Long id) {
        this.usuarioEnderecoService.excluirPeloId(id);
        return ResponseEntity.noContent().build();
    }
}
