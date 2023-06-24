package br.com.ael.food.repositories;

import br.com.ael.food.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UsuarioRepository
        extends JpaRepository<Usuario, Long>, PagingAndSortingRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
