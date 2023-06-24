package br.com.ael.food.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class EnderecoRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idEndereco;

    @NotNull(message = "Logradouro é obrigatório")
    @NotBlank(message = "Logradouro não pode ser em branco.")
    private String logradouro;

    @NotNull(message = "Numero é obrigatório")
    @NotBlank(message = "Numero não pode ser em branco.")
    private String numero;

    @NotNull(message = "Bairro é obrigatório")
    @NotBlank(message = "Bairro não pode ser em branco.")
    private String bairro;

    @NotNull(message = "CEP é obrigatório")
    @NotBlank(message = "CEP não pode ser em branco.")
    private String cep;

    @NotNull(message = "Cidade é obrigatório")
    @NotBlank(message = "Cidade não pode ser em branco.")
    private String cidade;

    private String estado;

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
