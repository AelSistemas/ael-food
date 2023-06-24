package br.com.ael.food.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_clienteWeb_endereco")
public class ClienteWebEndereco implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClienteWebEndereco;

    @ManyToOne
    @JoinColumn(name = "clienteWeb")
    private ClienteWeb clienteWeb;

    @ManyToOne
    @JoinColumn(name = "endereco")
    private Endereco endereco;

    public ClienteWeb getClienteWeb() {
        return clienteWeb;
    }

    public void setClienteWeb(ClienteWeb clienteWeb) {
        this.clienteWeb = clienteWeb;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
