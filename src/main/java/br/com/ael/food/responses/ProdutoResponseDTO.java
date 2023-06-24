package br.com.ael.food.responses;

import br.com.ael.food.responses.CategoriaResponseDTO;
import jakarta.persistence.*;

import java.io.Serializable;

public class ProdutoResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idProduto;

    private CategoriaResponseDTO categoria;

    private String nome;

    private String descricao;

    private String imagem;

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public CategoriaResponseDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaResponseDTO categoria) {
        this.categoria = categoria;
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
