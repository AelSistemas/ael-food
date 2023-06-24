package br.com.ael.food.enuns;

public enum StatusPedido {

    RECEBIDO("RECEBIDO"), PREPARACAO("PREPARAÇÃO"), FINALIZADO("FINALIZADO"),
    ENTREGUE("ENTREGUE"), CANCELADO("CANCELADO");

    private String descricao;

    public static StatusPedido buscarStatusPedido(String situacao) {
        StatusPedido statusPedido = null;
        for (StatusPedido sPedido: statusPedido.values()) {
            if (sPedido.getDescricao().equals(situacao.toUpperCase())) {
                statusPedido = sPedido;
                break;
            }
        }
        return statusPedido;
    }

    private StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
