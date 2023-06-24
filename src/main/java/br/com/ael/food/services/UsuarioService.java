package br.com.ael.food.services;

import br.com.ael.food.entities.*;
import br.com.ael.food.handlers.BadRequestException;
import br.com.ael.food.handlers.ObjetoNotFoundException;
import br.com.ael.food.repositories.UsuarioRepository;
import br.com.ael.food.requests.CategoriaRequestDTO;
import br.com.ael.food.requests.FiltroCategoriaDTO;
import br.com.ael.food.requests.FiltroUsuarioRequestDTO;
import br.com.ael.food.requests.UsuarioRequestDTO;
import br.com.ael.food.responses.CategoriaResponseDTO;
import br.com.ael.food.responses.UsuarioResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    private final ModelMapper modelMapper;

    private EnderecoService enderecoService;

    public UsuarioService(UsuarioRepository repository, ModelMapper modelMapper, EnderecoService enderecoService) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.enderecoService = enderecoService;
    }

    public UsuarioResponseDTO salvar(UsuarioRequestDTO usuarioRequestDTO) {
        this.repository.findByEmail(usuarioRequestDTO.getEmail()).ifPresent(usuario -> {
            throw new BadRequestException("Email já cadastrado!");
        });
        Usuario usuario = this.modelMapper.map(usuarioRequestDTO, Usuario.class);
        usuario = this.repository.save(usuario);
        return this.modelMapper.map(usuario, UsuarioResponseDTO.class);
    }

    public List<UsuarioResponseDTO> buscarTodos(PageRequest pageRequest) {
        return this.repository.findAll(pageRequest).stream().map(usuario -> {
            return this.modelMapper.map(usuario, UsuarioResponseDTO.class);
        }).collect(Collectors.toList());
    }

    public UsuarioResponseDTO buscarPeloId(Long id) {
        return this.repository.findById(id).map(usuario -> {
            return this.modelMapper.map(usuario, UsuarioResponseDTO.class);
        }).orElseThrow(() -> new ObjetoNotFoundException("Categoria não encontrada!"));
    }

    public void deletePeloId(Long id) {
        this.repository.findById(id).ifPresentOrElse(usuario -> {
                this.repository.delete(usuario);
        }, () -> {
            throw new ObjetoNotFoundException("Usuario não encontrado.");
        });
    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO usuarioRequestDTO) {
        return this.repository.findById(id).map(usuario -> {

            if(!usuario.getEmail().equals(usuarioRequestDTO.getEmail())) {
                this.repository.findByEmail(usuarioRequestDTO.getEmail()).ifPresent(user -> {
                    throw new BadRequestException("Email já cadastrado!");
                });
            }
            usuario = this.modelMapper.map(usuarioRequestDTO, Usuario.class);
            usuario = this.repository.save(usuario);
            return this.modelMapper.map(usuario, UsuarioResponseDTO.class);
        }).orElseThrow(() -> new ObjetoNotFoundException("Categoria não encontrada!"));
    }

    public List<UsuarioResponseDTO> filtroUsuario(FiltroUsuarioRequestDTO filtroUsuarioRequestDTO, PageRequest pageRequest) {
        Usuario usuario = this.modelMapper.map(filtroUsuarioRequestDTO, Usuario.class);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Usuario> example = Example.of(usuario, exampleMatcher);

        Page<Usuario> usuarios = this.repository.findAll(example, pageRequest);
        return usuarios.stream().map(user -> {
            return this.modelMapper.map(user, UsuarioResponseDTO.class);
        }).collect(Collectors.toList());
    }

    public Usuario buscarUsuarioPeloId(Long idUsuario) {
        return this.repository.findById(idUsuario).orElseThrow(() -> new ObjetoNotFoundException("Usuario não encontrado."));
    }
}
