package br.com.ael.food.requests;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class UsuarioRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idUsuario;

    @NotNull(message = "Nome é obrigatório")
    @NotBlank(message = "Nome não pode ser em branco.")
    private String nome;

    @NotNull(message = "Nome é obrigatório")
    @NotBlank(message = "Nome não pode ser em branco.")
    @Email
    private  String email;

    @NotNull(message = "Nome é obrigatório")
    @NotBlank(message = "Nome não pode ser em branco.")
    private String senha;


    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
