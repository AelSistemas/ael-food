package br.com.ael.food.repositories;

import br.com.ael.food.entities.Categoria;
import br.com.ael.food.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>, PagingAndSortingRepository<Endereco, Long> {
    Optional<Endereco> findByCep(String cep);
}
