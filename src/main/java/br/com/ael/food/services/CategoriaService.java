package br.com.ael.food.services;

import br.com.ael.food.entities.Categoria;
import br.com.ael.food.handlers.BadRequestException;
import br.com.ael.food.handlers.ObjetoNotFoundException;
import br.com.ael.food.repositories.CategoriaRepository;
import br.com.ael.food.requests.CategoriaRequestDTO;
import br.com.ael.food.requests.FiltroCategoriaDTO;
import br.com.ael.food.responses.CategoriaResponseDTO;
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
public class CategoriaService {

    private final CategoriaRepository repository;

    private final ModelMapper modelMapper;

    public CategoriaService(CategoriaRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public CategoriaResponseDTO salvar(CategoriaRequestDTO categoriaRequestDTO) {
        this.repository.findByNome(categoriaRequestDTO.getNome()).ifPresent(categoria -> {
            throw new BadRequestException("Nome de categoria já usado!");
        });
        Categoria categoria = this.modelMapper.map(categoriaRequestDTO, Categoria.class);
        categoria = this.repository.save(categoria);
        return this.modelMapper.map(categoria, CategoriaResponseDTO.class);
    }

    public List<CategoriaResponseDTO> buscarTodos(PageRequest pageRequest) {
        return this.repository.findAll(pageRequest).stream().map(categoria -> {
            return this.modelMapper.map(categoria, CategoriaResponseDTO.class);
        }).collect(Collectors.toList());
    }

    public CategoriaResponseDTO buscarPeloId(Long id) {
        return this.repository.findById(id).map(categoria -> {
            return this.modelMapper.map(categoria, CategoriaResponseDTO.class);
        }).orElseThrow(() -> new ObjetoNotFoundException("Categoria não encontrada!"));
    }

    public void deletePeloId(Long id) {
        this.repository.findById(id).ifPresentOrElse(categoria -> {
            try {

                this.repository.delete(categoria);
            } catch (DataIntegrityViolationException e) {
                throw new BadRequestException("Categoria vinculada com produto.");
            }
        }, () -> {
            throw new ObjetoNotFoundException("Categoria não encontrada.");
        });
    }

    public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO categoriaRequestDTO) {
        return this.repository.findById(id).map(categoria -> {
            if (!categoria.getNome().equals(categoriaRequestDTO.getNome())) {
                this.repository.findByNome(categoriaRequestDTO.getNome()).ifPresent(cat -> {
                    throw new BadRequestException("Nome de categoria já usado!");
                });
            }
            categoria.setIdCategoria(categoriaRequestDTO.getIdCategoria());
            return this.modelMapper.map(categoria, CategoriaResponseDTO.class);
        }).orElseThrow(() -> new ObjetoNotFoundException("Categoria não encontrada!"));
    }

    public List<CategoriaResponseDTO> filtroCategoria(FiltroCategoriaDTO filtroCategoriaDTO, PageRequest pageRequest) {
        Categoria categoria = this.modelMapper.map(filtroCategoriaDTO, Categoria.class);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Categoria> example = Example.of(categoria, exampleMatcher);

        Page<Categoria> categorias = this.repository.findAll(example, pageRequest);
        return categorias.stream().map(tur -> {
            return this.modelMapper.map(tur, CategoriaResponseDTO.class);
        }).collect(Collectors.toList());
    }

    public Categoria buscarCategoriaPeloId(Long idCategoria) {
        return this.repository.findById(idCategoria).orElseThrow(() -> new ObjetoNotFoundException("Categoria não encontrada."));
    }
}
