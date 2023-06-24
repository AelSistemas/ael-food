package br.com.ael.food.services;

import br.com.ael.food.entities.ClienteWeb;
import br.com.ael.food.entities.Endereco;
import br.com.ael.food.handlers.BadRequestException;
import br.com.ael.food.handlers.ObjetoNotFoundException;
import br.com.ael.food.repositories.ClienteWebRepository;
import br.com.ael.food.requests.ClienteWebRequestDTO;
import br.com.ael.food.requests.FiltroClienteWebRequestDTO;
import br.com.ael.food.responses.ClienteWebResponseDTO;
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
public class ClienteWebService {

    private final ClienteWebRepository repository;

    private final ModelMapper modelMapper;

    private final EnderecoService enderecoService;

    public ClienteWebService(ClienteWebRepository repository, ModelMapper modelMapper, EnderecoService enderecoService) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.enderecoService = enderecoService;
    }

    public ClienteWebResponseDTO salvar(ClienteWebRequestDTO  clienteWebRequestDTO) {
        ClienteWeb clienteWeb = this.modelMapper.map(clienteWebRequestDTO, ClienteWeb.class);
        clienteWeb = this.repository.save(clienteWeb);
        return this.modelMapper.map(clienteWeb, ClienteWebResponseDTO.class);
    }

    public List<ClienteWebResponseDTO> buscarTodos(PageRequest pageRequest) {
        return this.repository.findAll(pageRequest).stream().map(clienteWeb -> {
            return this.modelMapper.map(clienteWeb, ClienteWebResponseDTO.class);
        }).collect(Collectors.toList());
    }

    public ClienteWebResponseDTO buscarPeloId(Long id) {
        return this.repository.findById(id).map(clienteWeb -> {
            return this.modelMapper.map(clienteWeb, ClienteWebResponseDTO.class);
        }).orElseThrow(() -> new ObjetoNotFoundException("Cliente não encontrado!"));
    }

    public void deletePeloId(Long id) {
        this.repository.findById(id).ifPresentOrElse(clienteWeb -> {
            try {

                this.repository.delete(clienteWeb);
            } catch (DataIntegrityViolationException e) {
                throw new BadRequestException("Cliente vinculado a pedido.");
            }
        }, () -> {
            throw new ObjetoNotFoundException("Cliente não encontrado.");
        });
    }

    public ClienteWebResponseDTO atualizar(Long id, ClienteWebRequestDTO clienteWebRequestDTO) {
        return this.repository.findById(id).map(clienteWeb -> {
            clienteWeb = this.modelMapper.map(clienteWebRequestDTO, ClienteWeb.class);
            clienteWeb.setIdClienteWeb(clienteWebRequestDTO.getIdClienteWeb());
            clienteWeb = this.repository.save(clienteWeb);
            return this.modelMapper.map(clienteWeb, ClienteWebResponseDTO.class);
        }).orElseThrow(() -> new ObjetoNotFoundException("Categoria não encontrada!"));
    }

    public List<ClienteWebResponseDTO> filtroClienteWeb(FiltroClienteWebRequestDTO filtroClienteWebRequestDTO, PageRequest pageRequest) {
        ClienteWeb clienteWeb = this.modelMapper.map(filtroClienteWebRequestDTO, ClienteWeb.class);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<ClienteWeb> example = Example.of(clienteWeb, exampleMatcher);

        Page<ClienteWeb> clienteWebs = this.repository.findAll(example, pageRequest);
        return clienteWebs.stream().map(cw -> {
            return this.modelMapper.map(cw, ClienteWebResponseDTO.class);
        }).collect(Collectors.toList());
    }

    public ClienteWeb buscarClienteWebPeloId(Long idClenteWeb) {
        return this.repository.findById(idClenteWeb).orElseThrow(() -> new ObjetoNotFoundException("Cliente não encontrado."));
    }
}
