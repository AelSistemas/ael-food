package br.com.ael.food.services;

import br.com.ael.food.entities.Endereco;
import br.com.ael.food.entities.Usuario;
import br.com.ael.food.entities.UsuarioEndereco;
import br.com.ael.food.handlers.ObjetoNotFoundException;
import br.com.ael.food.repositories.UsuarioEnderecoRepository;
import br.com.ael.food.requests.UsuarioEnderecoRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class UsuarioEnderecoService {

    private final UsuarioService usuarioService;

    private final EnderecoService enderecoService;

    private final UsuarioEnderecoRepository usuarioEnderecoRepository;

    public UsuarioEnderecoService(UsuarioService usuarioService, EnderecoService enderecoService, UsuarioEnderecoRepository usuarioEnderecoRepository) {
        this.usuarioService = usuarioService;
        this.enderecoService = enderecoService;
        this.usuarioEnderecoRepository = usuarioEnderecoRepository;
    }

    public void save(UsuarioEnderecoRequestDTO usuarioEnderecoRequestDTO) {

        Usuario usuario = this.usuarioService.buscarUsuarioPeloId(usuarioEnderecoRequestDTO.getIdUsuario());
        Endereco endereco = this.enderecoService.buscarEnderecoPeloId(usuarioEnderecoRequestDTO.getIdEndereco());

        UsuarioEndereco usuarioEndereco = new UsuarioEndereco();
        usuarioEndereco.setEndereco(endereco);
        usuarioEndereco.setUsuario(usuario);
        this.usuarioEnderecoRepository.save(usuarioEndereco);
    }


    public void excluirPeloId(Long id) {
        this.usuarioEnderecoRepository.findById(id).ifPresentOrElse(ue -> {
            this.usuarioEnderecoRepository.delete(ue);
        }, () -> {
            throw new ObjetoNotFoundException("Usuario e endereço  não encontrado.");
        });
    }
}
