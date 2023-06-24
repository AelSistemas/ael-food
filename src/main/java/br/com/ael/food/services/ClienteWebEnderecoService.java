package br.com.ael.food.services;

import br.com.ael.food.entities.ClienteWeb;
import br.com.ael.food.entities.ClienteWebEndereco;
import br.com.ael.food.entities.Endereco;
import br.com.ael.food.handlers.ObjetoNotFoundException;
import br.com.ael.food.repositories.ClienteWEBEnderecoRepository;
import br.com.ael.food.requests.ClienteWebEnderecoRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class ClienteWebEnderecoService {

    private final ClienteWebService clienteWebService;

    private final EnderecoService enderecoService;

    private final ClienteWEBEnderecoRepository clienteWEBEnderecoRepository;

    public ClienteWebEnderecoService(ClienteWebService clienteWebService, EnderecoService enderecoService, ClienteWEBEnderecoRepository clienteWEBEnderecoRepository) {
        this.clienteWebService = clienteWebService;
        this.enderecoService = enderecoService;
        this.clienteWEBEnderecoRepository = clienteWEBEnderecoRepository;
    }

    public void save(ClienteWebEnderecoRequestDTO clienteWebEnderecoRequestDTO) {

        ClienteWeb clienteWeb = this.clienteWebService.buscarClienteWebPeloId(clienteWebEnderecoRequestDTO.getIdClienteWeb());
        Endereco endereco = this.enderecoService.buscarEnderecoPeloId(clienteWebEnderecoRequestDTO.getIdEndereco());

        ClienteWebEndereco clienteWebEndereco = new ClienteWebEndereco();
        clienteWebEndereco.setEndereco(endereco);
        clienteWebEndereco.setClienteWeb(clienteWeb);
        this.clienteWEBEnderecoRepository.save(clienteWebEndereco);
    }


    public void excluirPeloId(Long id) {
        this.clienteWEBEnderecoRepository.findById(id).ifPresentOrElse(ce -> {
            this.clienteWEBEnderecoRepository.delete(ce);
        }, () -> {
            throw new ObjetoNotFoundException("Cliente e endereço  não encontrado.");
        });
    }
}
