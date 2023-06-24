package br.com.ael.food.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class ClienteWebRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idClienteWeb;

    @NotNull(message = "Nome é obrigatório")
    @NotBlank(message = "Nome não pode ser em branco.")
    private String nome;

    @NotNull(message = "Contato é obrigatório")
    @NotBlank(message = "Contato não pode ser em branco.")
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
