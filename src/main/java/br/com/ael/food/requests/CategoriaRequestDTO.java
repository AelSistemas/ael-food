package br.com.ael.food.requests;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;


public class CategoriaRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idCategoria;

    @NotNull(message = "Nome é obrigatório")
    @NotBlank(message = "Nome não pode ser em branco.")
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
