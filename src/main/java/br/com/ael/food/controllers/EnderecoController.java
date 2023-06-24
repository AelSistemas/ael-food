package br.com.ael.food.controllers;

import br.com.ael.food.requests.EnderecoRequestDTO;
import br.com.ael.food.requests.FiltroEnderecoRequestDTO;
import br.com.ael.food.responses.EnderecoResponseDTO;
import br.com.ael.food.services.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("enderecos")
public class EnderecoController {

    private EnderecoService service;

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> salvar(@Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO) {
        return ResponseEntity.ok(this.service.salvar(enderecoRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<EnderecoResponseDTO>> buscarTodos(@RequestParam Integer pagina,
                                                                  @RequestParam Integer quantidade,
                                                                  @RequestParam String ordem,
                                                                  @RequestParam String ordenarPor) {
        return ResponseEntity.ok(this.service
                .buscarTodos(PageRequest.of(pagina, quantidade, Sort.by(Sort.Direction.valueOf(ordem), ordenarPor))));

    }
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> buscarPeloId(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(this.service.buscarPeloId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeloId(@PathVariable(name = "id") Long id) {
        this.service.deletePeloId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> atualizar(@PathVariable(name = "id") Long id,
                                                          @Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO) {
        return ResponseEntity.ok(this.service.atualizar(id, enderecoRequestDTO));
    }
    @PostMapping("/filtroEndereco")
    public ResponseEntity<List<EnderecoResponseDTO>> filtroEndereco(
            @RequestBody FiltroEnderecoRequestDTO filtroEnderecoRequestDTO,
            @RequestParam Integer pagina,
            @RequestParam Integer quantidade,
            @RequestParam String ordem,
            @RequestParam String ordenarPor) {
        return ResponseEntity.ok(this.service.filtroEndereco(filtroEnderecoRequestDTO,
                PageRequest.of(pagina, quantidade, Sort.by(Sort.Direction.valueOf(ordem),
                        ordenarPor))));
    }


}
