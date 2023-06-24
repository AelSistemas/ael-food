package br.com.ael.food.controllers;

import br.com.ael.food.requests.CategoriaRequestDTO;
import br.com.ael.food.requests.FiltroCategoriaDTO;
import br.com.ael.food.responses.CategoriaResponseDTO;
import br.com.ael.food.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> salvar(@Valid @RequestBody CategoriaRequestDTO categoriaRequestDTO) {
        return ResponseEntity.ok(this.service.salvar(categoriaRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> buscarTodos(@RequestParam Integer pagina,
                                                                  @RequestParam Integer quantidade,
                                                                  @RequestParam String ordem,
                                                                  @RequestParam String ordenarPor) {
        return ResponseEntity.ok(this.service
                .buscarTodos(PageRequest.of(pagina, quantidade, Sort.by(Sort.Direction.valueOf(ordem), ordenarPor))));

    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> buscarPeloId(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(this.service.buscarPeloId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeloId(@PathVariable(name = "id") Long id) {
        this.service.deletePeloId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable(name = "id") Long id,
                                                          @Valid @RequestBody CategoriaRequestDTO categoriaRequestDTO) {
        return ResponseEntity.ok(this.service.atualizar(id, categoriaRequestDTO));
    }

    @PostMapping("/filtroCategoria")
    public ResponseEntity<List<CategoriaResponseDTO>> filtroCategoria(
            @RequestBody FiltroCategoriaDTO filtroCategoriaDTO,
            @RequestParam Integer pagina,
            @RequestParam Integer quantidade,
            @RequestParam String ordem,
            @RequestParam String ordenarPor) {
        return ResponseEntity.ok(this.service.filtroCategoria(filtroCategoriaDTO,
                PageRequest.of(pagina, quantidade, Sort.by(Sort.Direction.valueOf(ordem),
                        ordenarPor))));
    }

}
