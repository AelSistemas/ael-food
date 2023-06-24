package br.com.ael.food.requests;

import java.io.Serializable;

public class UsuarioEnderecoRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idUsuario;

    private Long idEndereco;


    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }
}
