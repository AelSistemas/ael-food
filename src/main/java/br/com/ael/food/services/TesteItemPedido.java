package br.com.ael.food.services;

import br.com.ael.food.entities.Categoria;
import br.com.ael.food.entities.ItemPedido;
import br.com.ael.food.entities.Produto;
import br.com.ael.food.handlers.BadRequestException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TesteItemPedido {

    public static void main(String[] args) {
        List<ItemPedido> itemPedidos = new ArrayList<ItemPedido>();

        Categoria categoria = Categoria.builder().idCategoria(1l).nome("alimnetos").build();
        Produto produto = new Produto();
        produto.setIdProduto(1L);
        produto.setNome("cerveja");
        produto.setCategoria(categoria);
        produto.setPreco(new BigDecimal(3));

//        Produto produto1 = new Produto();
//        produto1.setIdProduto(2L);
//        produto1.setNome("coca");
//        produto1.setCategoria(categoria);
//        produto1.setPreco(new BigDecimal(2));

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(2);
        itemPedido.setPrecoUnitario(produto.getPreco());
        itemPedidos.add(itemPedido);

//        ItemPedido itemPedido1 = new ItemPedido();
//        itemPedido1.setProduto(produto1);
//        itemPedido1.setQuantidade(3);
//        itemPedido1.setPrecoUnitario(produto1.getPreco());
//        itemPedidos.add(itemPedido1);

        System.out.println("lista antes de atualizar a quantidade " + itemPedidos.toString());

        Optional<ItemPedido> itemPedidoOptional = null;
        if(!itemPedidos.isEmpty()) {
            itemPedidoOptional = itemPedidos.stream()
                    .filter(item -> item.getProduto().getIdProduto().equals(produto.getIdProduto()))
                    .findAny();

            itemPedidoOptional.get().setQuantidade(6);
            System.out.println("Quantidade alterada " + itemPedidoOptional.get().toString());
        }

        System.out.println("lista depois de atualizar a quantidade " + itemPedidos.toString());
    }
}
