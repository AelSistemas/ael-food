package br.com.ael.food.requests;

import br.com.ael.food.enuns.FormaPamento;
import br.com.ael.food.enuns.StatusPedido;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

public class PedidoRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idPedido;

    private BigDecimal total;

    private String descricao;

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
