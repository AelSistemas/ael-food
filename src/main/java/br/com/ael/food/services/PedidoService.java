package br.com.ael.food.services;

import br.com.ael.food.entities.Pedido;
import br.com.ael.food.enuns.StatusPedido;
import br.com.ael.food.repositories.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public Pedido salvarPedido(Pedido pedido) {
        return  this.repository.save(pedido);
    }

    public  void  preparacao (Pedido pedido){
        pedido.setStatusPedido(StatusPedido.PREPARACAO);
        this.repository.save(pedido);
    }

    public  void  finalizando (Pedido pedido){
        pedido.setStatusPedido(StatusPedido.FINALIZADO);
        this.repository.save(pedido);
    }

    public  void  entregue (Pedido pedido){
        pedido.setStatusPedido(StatusPedido.ENTREGUE);
        this.repository.save(pedido);
    }

    public  void  cancelado (Pedido pedido){
        pedido.setStatusPedido(StatusPedido.CANCELADO);
        this.repository.save(pedido);
    }
}
