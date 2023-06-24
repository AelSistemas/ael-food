package br.com.ael.food.requests;

import java.io.Serializable;

public class FiltroCategoriaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idCategoria;

    private String nome;

    private  String descricao;

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
}
