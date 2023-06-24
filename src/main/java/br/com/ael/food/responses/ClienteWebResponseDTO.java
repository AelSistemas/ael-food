package br.com.ael.food.responses;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class ClienteWebResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
