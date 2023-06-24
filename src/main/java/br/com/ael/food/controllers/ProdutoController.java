package br.com.ael.food.controllers;

import br.com.ael.food.requests.FiltroProdutoRequestDTO;
import br.com.ael.food.requests.ProdutoRequestDTO;
import br.com.ael.food.responses.ProdutoResponseDTO;
import br.com.ael.food.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvar(@Valid @RequestBody ProdutoRequestDTO produtoRequestDTO) {
        return ResponseEntity.ok(this.service.salvar(produtoRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> buscarTodos(@RequestParam Integer pagina,
                                                                  @RequestParam Integer quantidade,
                                                                  @RequestParam String ordem,
                                                                  @RequestParam String ordenarPor) {
        return ResponseEntity.ok(this.service
                .buscarTodos(PageRequest.of(pagina, quantidade, Sort.by(Sort.Direction.valueOf(ordem), ordenarPor))));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPeloId(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(this.service.buscarPeloId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeloId(@PathVariable(name = "id") Long id) {
        this.service.deletePeloId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable(name = "id") Long id,
                                                          @Valid @RequestBody ProdutoRequestDTO produtoRequestDTO) {
        return ResponseEntity.ok(this.service.atualizar(id, produtoRequestDTO));
    }

    @PostMapping("/filtroProduto")
    public ResponseEntity<List<ProdutoResponseDTO>> filtroProduto(
            @RequestBody FiltroProdutoRequestDTO filtroProdutoRequestDTO,
            @RequestParam Integer pagina,
            @RequestParam Integer quantidade,
            @RequestParam String ordem,
            @RequestParam String ordenarPor) {
        return ResponseEntity.ok(this.service.filtroProduto(filtroProdutoRequestDTO,
                PageRequest.of(pagina, quantidade, Sort.by(Sort.Direction.valueOf(ordem),
                        ordenarPor))));
    }

}
