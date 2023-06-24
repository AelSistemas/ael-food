package br.com.ael.food.requests;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class ProdutoRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idProduto;

    private Long idCategoria;

    @NotNull(message = "Nome é obrigatório")
    @NotBlank(message = "Nome não pode ser em branco.")
    private String nome;

    private String descricao;

    @NotNull(message = "Imagem do produto é obrigatório")
    @NotBlank(message = "Imagem do produto não pode ser em branco.")
    private String imagem;

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
