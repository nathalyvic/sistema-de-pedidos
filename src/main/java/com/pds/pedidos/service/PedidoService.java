package com.pds.pedidos.service;

import com.pds.pedidos.domain.Pedido;
import com.pds.pedidos.exception.RecursoNaoEncontradoException;
import com.pds.pedidos.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public PedidoService(PedidoRepository repository,
                         ClienteService clienteService,
                         ProdutoService produtoService) {
        this.repository = repository;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

    public List<Pedido> listar() {
        return repository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado: " + id));
    }

    public Pedido salvar(Pedido pedido) {
        pedido.setCliente(clienteService.buscarPorId(pedido.getCliente().getId()));
        pedido.setProduto(produtoService.buscarPorId(pedido.getProduto().getId()));
        return repository.save(pedido);
    }

    public Pedido atualizar(Long id, Pedido dados) {
        Pedido pedido = buscarPorId(id);
        pedido.setData(dados.getData());
        pedido.setCliente(clienteService.buscarPorId(dados.getCliente().getId()));
        pedido.setProduto(produtoService.buscarPorId(dados.getProduto().getId()));
        return repository.save(pedido);
    }

    public void excluir(Long id) {
        repository.delete(buscarPorId(id));
    }
}