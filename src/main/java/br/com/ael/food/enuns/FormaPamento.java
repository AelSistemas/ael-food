package br.com.ael.food.enuns;

public enum FormaPamento {

    CREDITO("CREDITO"), DEBITO("DEBITO"), PIX("PIX"),
    DINHEIRO("DINHEIRO");

    private String descricao;

    public static FormaPamento buscarFormaPamento(String pagamento) {
        FormaPamento  formaPamento = null;
        for (FormaPamento fPagamento: FormaPamento.values()) {
            if (fPagamento.getDescricao().equals(pagamento.toUpperCase())) {
                formaPamento = fPagamento;
                break;
            }
        }
        return formaPamento;
    }

    private FormaPamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
