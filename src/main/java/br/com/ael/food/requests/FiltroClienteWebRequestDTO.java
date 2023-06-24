package br.com.ael.food.requests;

import java.io.Serializable;

public class FiltroClienteWebRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idClienteWeb;

    private String nome;

    private String contato;

    public Long getIdClienteWeb() {
        return idClienteWeb;
    }

    public void setIdClienteWeb(Long idClienteWeb) {
        this.idClienteWeb = idClienteWeb;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}
