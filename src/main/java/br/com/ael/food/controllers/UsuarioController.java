package br.com.ael.food.controllers;

import br.com.ael.food.requests.FiltroUsuarioRequestDTO;
import br.com.ael.food.requests.UsuarioRequestDTO;
import br.com.ael.food.responses.UsuarioResponseDTO;
import br.com.ael.food.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> salvar(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return ResponseEntity.ok(this.service.salvar(usuarioRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> buscarTodos(@RequestParam Integer pagina,
                                                                  @RequestParam Integer quantidade,
                                                                  @RequestParam String ordem,
                                                                  @RequestParam String ordenarPor) {
        return ResponseEntity.ok(this.service
                .buscarTodos(PageRequest.of(pagina, quantidade, Sort.by(Sort.Direction.valueOf(ordem), ordenarPor))));

    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPeloId(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(this.service.buscarPeloId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeloId(@PathVariable(name = "id") Long id) {
        this.service.deletePeloId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable(name = "id") Long id,
                                                          @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return ResponseEntity.ok(this.service.atualizar(id, usuarioRequestDTO));
    }

    @PostMapping("/filtroUsuario")
    public ResponseEntity<List<UsuarioResponseDTO>> filtroUsuario(
            @RequestBody FiltroUsuarioRequestDTO filtroUsuarioRequestDTO,
            @RequestParam Integer pagina,
            @RequestParam Integer quantidade,
            @RequestParam String ordem,
            @RequestParam String ordenarPor) {
        return ResponseEntity.ok(this.service.filtroUsuario(filtroUsuarioRequestDTO,
                PageRequest.of(pagina, quantidade, Sort.by(Sort.Direction.valueOf(ordem),
                        ordenarPor))));
    }

}
