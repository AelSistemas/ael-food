package br.com.ael.food.repositories;

import br.com.ael.food.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProdutoRepository
        extends JpaRepository<Produto, Long>, PagingAndSortingRepository<Produto, Long> {
    Optional<Produto> findByNome(String nome);
}
