package br.com.ael.food.repositories;

import br.com.ael.food.entities.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>, PagingAndSortingRepository<ItemPedido, Long> {

}
