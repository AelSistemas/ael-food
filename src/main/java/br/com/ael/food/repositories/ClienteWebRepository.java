package br.com.ael.food.repositories;

import br.com.ael.food.entities.Categoria;
import br.com.ael.food.entities.ClienteWeb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ClienteWebRepository extends JpaRepository<ClienteWeb, Long>, PagingAndSortingRepository<ClienteWeb, Long> {

}

