package br.com.ael.food.requests;

import br.com.ael.food.entities.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idItemPedido;

    private List<Long> listaIdsProduto;

    private String formaPagamento;

    private String descricao;

    private Long idEndereco;
    private Long idClienteWeb;
    private Long idUsuario;


}
