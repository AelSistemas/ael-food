package br.com.ael.food.services;

import br.com.ael.food.entities.Categoria;
import br.com.ael.food.entities.Endereco;
import br.com.ael.food.handlers.BadRequestException;
import br.com.ael.food.handlers.ObjetoNotFoundException;
import br.com.ael.food.repositories.EnderecoRepository;
import br.com.ael.food.requests.EnderecoRequestDTO;
import br.com.ael.food.requests.FiltroCategoriaDTO;
import br.com.ael.food.requests.FiltroEnderecoRequestDTO;
import br.com.ael.food.responses.EnderecoResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    private  final EnderecoRepository repository;

    private final ModelMapper modelMapper;

    public EnderecoService(EnderecoRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public EnderecoResponseDTO salvar(EnderecoRequestDTO enderecoRequestDTO) {
        Endereco endereco = this.modelMapper.map(enderecoRequestDTO, Endereco.class);
        endereco = this.repository.save(endereco);
        return this.modelMapper.map(endereco, EnderecoResponseDTO.class);
    }

    public List<EnderecoResponseDTO> buscarTodos(PageRequest pageRequest) {
        return this.repository.findAll(pageRequest).stream().map(endereco -> {
            return this.modelMapper.map(endereco, EnderecoResponseDTO.class);
        }).collect(Collectors.toList());
    }

    public EnderecoResponseDTO buscarPeloId(Long id) {
        return this.repository.findById(id).map(endereco -> {
            return this.modelMapper.map(endereco, EnderecoResponseDTO.class);
        }).orElseThrow(() ->  new ObjetoNotFoundException("Endereco não encontrado!"));
    }

    public void deletePeloId(Long id) {
        this.repository.findById(id).ifPresentOrElse(endereco -> {
            try {

                this.repository.delete(endereco);
            } catch (DataIntegrityViolationException e) {
                throw new BadRequestException("Endereço vinculado.");
            }
        }, () -> {
            throw new ObjetoNotFoundException("Endereço não encontrada.");
        });
    }

    public EnderecoResponseDTO atualizar(Long id, EnderecoRequestDTO enderecoRequestDTO) {
        Endereco endereco = this.modelMapper.map(enderecoRequestDTO, Endereco.class);
        endereco = this.repository.save(endereco);
        return this.modelMapper.map(endereco, EnderecoResponseDTO.class);
    }

    public List<EnderecoResponseDTO> filtroEndereco(FiltroEnderecoRequestDTO filtroEnderecoRequestDTO, PageRequest pageRequest) {
        Endereco endereco = this.modelMapper.map(filtroEnderecoRequestDTO, Endereco.class);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Endereco> example = Example.of(endereco, exampleMatcher);

        Page<Endereco> enderecos = this.repository.findAll(example, pageRequest);
        return enderecos.stream().map(end -> {
            return this.modelMapper.map(end, EnderecoResponseDTO.class);
        }).collect(Collectors.toList());
    }

    public Endereco buscarEnderecoPeloId(Long idEndereco) {
        return this.repository.findById(idEndereco).orElseThrow(() -> new ObjetoNotFoundException("Endereco não encontrada."));
    }
}
