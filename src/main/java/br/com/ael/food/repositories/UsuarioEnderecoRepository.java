package br.com.ael.food.repositories;

import br.com.ael.food.entities.UsuarioEndereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioEnderecoRepository
        extends JpaRepository<UsuarioEndereco, Long>, PagingAndSortingRepository<UsuarioEndereco, Long> {
}
