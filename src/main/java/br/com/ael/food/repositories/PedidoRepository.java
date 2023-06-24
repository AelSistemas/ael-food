package br.com.ael.food.repositories;

import br.com.ael.food.entities.Categoria;
import br.com.ael.food.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long>, PagingAndSortingRepository<Pedido, Long> {
}
