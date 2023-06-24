package br.com.ael.food.controllers;

import br.com.ael.food.requests.ClienteWebEnderecoRequestDTO;
import br.com.ael.food.services.ClienteWebEnderecoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cliente-enderecos")
public class ClienteEnderecoController {

    private  final ClienteWebEnderecoService clienteEnderecoService;

    public ClienteEnderecoController(ClienteWebEnderecoService clienteEnderecoService) {
        this.clienteEnderecoService = clienteEnderecoService;
    }

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody ClienteWebEnderecoRequestDTO clienteWebEnderecoRequestDTO) {
        this.clienteEnderecoService.save(clienteWebEnderecoRequestDTO);
        return ResponseEntity.ok("sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPeloId(@PathVariable(name = "id") Long id) {
        this.clienteEnderecoService.excluirPeloId(id);
        return ResponseEntity.noContent().build();
    }
}
