package br.com.ael.food.repositories;

import br.com.ael.food.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>, PagingAndSortingRepository<Categoria, Long> {
    Optional<Categoria> findByNome(String nome);
}

