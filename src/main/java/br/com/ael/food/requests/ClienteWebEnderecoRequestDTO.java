package br.com.ael.food.requests;

import java.io.Serializable;

public class ClienteWebEnderecoRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idClienteWeb;

    private Long idEndereco;

    public Long getIdClienteWeb() {
        return idClienteWeb;
    }

    public void setIdClienteWeb(Long idClienteWeb) {
        this.idClienteWeb = idClienteWeb;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }
}
