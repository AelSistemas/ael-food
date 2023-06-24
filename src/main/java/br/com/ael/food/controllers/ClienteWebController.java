package br.com.ael.food.controllers;

import br.com.ael.food.requests.CategoriaRequestDTO;
import br.com.ael.food.requests.ClienteWebRequestDTO;
import br.com.ael.food.requests.FiltroCategoriaDTO;
import br.com.ael.food.requests.FiltroClienteWebRequestDTO;
import br.com.ael.food.responses.CategoriaResponseDTO;
import br.com.ael.food.responses.ClienteWebResponseDTO;
import br.com.ael.food.services.CategoriaService;
import br.com.ael.food.services.ClienteWebService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes-web")
public class ClienteWebController {

    private final ClienteWebService service;

    public ClienteWebController(ClienteWebService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ClienteWebResponseDTO> salvar(@Valid @RequestBody ClienteWebRequestDTO clienteWebRequestDTO) {
        return ResponseEntity.ok(this.service.salvar(clienteWebRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<ClienteWebResponseDTO>> buscarTodos(@RequestParam Integer pagina,
                                                                  @RequestParam Integer quantidade,
                                                                  @RequestParam String ordem,
                                                                  @RequestParam String ordenarPor) {
        return ResponseEntity.ok(this.service
                .buscarTodos(PageRequest.of(pagina, quantidade, Sort.by(Sort.Direction.valueOf(ordem), ordenarPor))));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteWebResponseDTO> buscarPeloId(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(this.service.buscarPeloId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeloId(@PathVariable(name = "id") Long id) {
        this.service.deletePeloId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteWebResponseDTO> atualizar(@PathVariable(name = "id") Long id,
                                                          @Valid @RequestBody ClienteWebRequestDTO clienteWebRequestDTO) {
        return ResponseEntity.ok(this.service.atualizar(id, clienteWebRequestDTO));
    }

    @PostMapping("/filtroCategoria")
    public ResponseEntity<List<ClienteWebResponseDTO>> filtroClienteWeb(
            @RequestBody FiltroClienteWebRequestDTO filtroClienteWebRequestDTO,
            @RequestParam Integer pagina,
            @RequestParam Integer quantidade,
            @RequestParam String ordem,
            @RequestParam String ordenarPor) {
        return ResponseEntity.ok(this.service.filtroClienteWeb(filtroClienteWebRequestDTO,
                PageRequest.of(pagina, quantidade, Sort.by(Sort.Direction.valueOf(ordem),
                        ordenarPor))));
    }

}
