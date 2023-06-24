package br.com.ael.food.services;

import br.com.ael.food.entities.*;
import br.com.ael.food.enuns.FormaPamento;
import br.com.ael.food.enuns.StatusPedido;
import br.com.ael.food.repositories.ItemPedidoRepository;
import br.com.ael.food.requests.ItemPedidoRequestDTO;
import br.com.ael.food.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;

    private final ModelMapper modelMapper;

    private final ProdutoService produtoService;

    private final PedidoService pedidoService;

    private final EnderecoService enderecoService;

    private final ClienteWebService clienteWebService;

    private final UsuarioService usuarioService;

    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository, ModelMapper modelMapper, ProdutoService produtoService, PedidoService pedidoService, EnderecoService enderecoService, ClienteWebService clienteWebService, UsuarioService usuarioService) {
        this.itemPedidoRepository = itemPedidoRepository;
        this.modelMapper = modelMapper;
        this.produtoService = produtoService;
        this.pedidoService = pedidoService;
        this.enderecoService = enderecoService;
        this.clienteWebService = clienteWebService;
        this.usuarioService = usuarioService;
    }

    public void finalizar(ItemPedidoRequestDTO itemPedidoRequestDTO) {

        List<ItemPedido> itemPedidos = itemPedidoRequestDTO.getListaIdsProduto().stream().map(idProduto -> {
            Produto produto = this.produtoService.buscarProdutoPeloId(idProduto);
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(produto.getQuantidade());
            itemPedido.setPrecoUnitario(produto.getPreco());
            return itemPedido;
        }).collect(Collectors.toList());

        AtomicReference<BigDecimal> valorTotalAtual = new AtomicReference<>(BigDecimal.ZERO);

        Endereco endereco = this.enderecoService.buscarEnderecoPeloId(itemPedidoRequestDTO.getIdEndereco());

        Usuario usuario = null;
        ClienteWeb clienteWeb = null;
        if(!(itemPedidoRequestDTO.getIdUsuario() == null)) {
            usuario = this.usuarioService.buscarUsuarioPeloId(itemPedidoRequestDTO.getIdUsuario());
        }else{
            clienteWeb = this.clienteWebService.buscarClienteWebPeloId(itemPedidoRequestDTO.getIdClienteWeb());
        }

        Pedido pedido = new Pedido();
        pedido.setStatusPedido(StatusPedido.RECEBIDO);
        pedido.setDescricao(itemPedidoRequestDTO.getDescricao());
        pedido.setFormaPamento(FormaPamento.buscarFormaPamento(itemPedidoRequestDTO.getFormaPagamento()));
        pedido.setNumero(UUID.randomUUID().toString());
        pedido.setEndereco(endereco);

        if(!(usuario == null)){
            pedido.setUsuario(usuario);
        }else {
            pedido.setClienteWeb(clienteWeb);
        }

        pedido = this.pedidoService.salvarPedido(pedido);

        Pedido finalPedido = pedido;
        itemPedidos.forEach(itemPedido -> {
            itemPedido.setPedido(finalPedido);
            BigDecimal valorItemPedido = itemPedido.getPrecoUnitario().multiply(new BigDecimal(itemPedido.getQuantidade()));
            valorTotalAtual.set(valorItemPedido.add(valorTotalAtual.get()));
            this.itemPedidoRepository.save(itemPedido);
        });

        pedido.setTotal(valorTotalAtual.get());
        this.pedidoService.salvarPedido(pedido);
    }
}
