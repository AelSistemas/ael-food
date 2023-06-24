package br.com.ael.food.repositories;

import br.com.ael.food.entities.ClienteWebEndereco;
import br.com.ael.food.services.ClienteWebEnderecoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClienteWEBEnderecoRepository
        extends JpaRepository<ClienteWebEndereco, Long>, PagingAndSortingRepository<ClienteWebEndereco, Long> {
}