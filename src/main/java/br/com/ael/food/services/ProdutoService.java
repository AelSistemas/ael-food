package br.com.ael.food.services;

import br.com.ael.food.entities.*;
import br.com.ael.food.handlers.BadRequestException;
import br.com.ael.food.handlers.ObjetoNotFoundException;
import br.com.ael.food.repositories.ProdutoRepository;
import br.com.ael.food.requests.ClienteWebRequestDTO;
import br.com.ael.food.requests.FiltroClienteWebRequestDTO;
import br.com.ael.food.requests.FiltroProdutoRequestDTO;
import br.com.ael.food.requests.ProdutoRequestDTO;
import br.com.ael.food.responses.ClienteWebResponseDTO;
import br.com.ael.food.responses.ProdutoResponseDTO;
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
public class ProdutoService {

    private final ProdutoRepository repository;

    private final ModelMapper modelMapper;

    public ProdutoService(ProdutoRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public ProdutoResponseDTO salvar(ProdutoRequestDTO produtoRequestDTO) {
        this.repository.findByNome(produtoRequestDTO.getNome()).ifPresent(produto -> {
            throw new BadRequestException("Nome do produto já usado!");
        });
        Produto produto = this.modelMapper.map(produtoRequestDTO, Produto.class);
        produto = this.repository.save(produto);
        return this.modelMapper.map(produto, ProdutoResponseDTO.class);
    }

    public List<ProdutoResponseDTO> buscarTodos(PageRequest pageRequest) {
        return this.repository.findAll(pageRequest).stream().map(produto -> {
            return this.modelMapper.map(produto, ProdutoResponseDTO.class);
        }).collect(Collectors.toList());
    }

    public ProdutoResponseDTO buscarPeloId(Long id) {
        return this.repository.findById(id).map(produto -> {
            return this.modelMapper.map(produto, ProdutoResponseDTO.class);
        }).orElseThrow(() -> new ObjetoNotFoundException("Produto não encontrado!"));
    }

    public void deletePeloId(Long id) {
        this.repository.findById(id).ifPresentOrElse(produto -> {
            try {

                this.repository.delete(produto);
            } catch (DataIntegrityViolationException e) {
                throw new BadRequestException("Produto vinculado a pedido.");
            }
        }, () -> {
            throw new ObjetoNotFoundException("Produto não encontrado.");
        });
    }

    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO produtoRequestDTO) {
        return this.repository.findById(id).map(produto -> {
            if (!produto.getNome().equals(produtoRequestDTO.getNome())) {
                this.repository.findByNome(produtoRequestDTO.getNome()).ifPresent(p -> {
                    throw new BadRequestException("Nome do produto já usado!");
                });
            }
            produto.setIdProduto(produtoRequestDTO.getIdProduto());
            produto = this.modelMapper.map(produtoRequestDTO, Produto.class);
            produto = this.repository.save(produto);
            return this.modelMapper.map(produto, ProdutoResponseDTO.class);
        }).orElseThrow(() -> new ObjetoNotFoundException("Produto não encontrado!"));
    }

    public List<ProdutoResponseDTO> filtroProduto(FiltroProdutoRequestDTO filtroProdutoRequestDTO, PageRequest pageRequest) {
        Produto produto = this.modelMapper.map(filtroProdutoRequestDTO, Produto.class);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Produto> example = Example.of(produto, exampleMatcher);

        Page<Produto> produtos = this.repository.findAll(example, pageRequest);
        return produtos.stream().map(produto1 -> {
            return this.modelMapper.map(produto1, ProdutoResponseDTO.class);
        }).collect(Collectors.toList());
    }

    public Produto buscarProdutoPeloId(Long idProduto) {
        return this.repository.findById(idProduto).orElseThrow(() -> new ObjetoNotFoundException("Produto não encontrado."));
    }
}
