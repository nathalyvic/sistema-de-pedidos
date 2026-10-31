package com.pds.pedidos.service;

import com.pds.pedidos.domain.Cliente;
import com.pds.pedidos.exception.RecursoNaoEncontradoException;
import com.pds.pedidos.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    // construtor: o que roda quando o objeto é criado, com o mesmo nome da classe, igual ao do aviso de “acabou”.
    public ClienteService(ClienteRepository repository) {    // ao criar o service, o Spring entrega o repository
        this.repository = repository;  // guarda o repository recebido para usar nos métodos
    }

    public List<Cliente> listar() {       // devolve a lista de todos os clientes
        return repository.findAll();     // pede ao repository para buscar todos os clientes no banco
    }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado: " + id)); // se não encontrar, dispara o erro "Cliente não encontrado" com o número pedido
    }

    public Cliente salvar(Cliente cliente) {                // recebe um cliente novo e devolve ele salvo, já com id
        return repository.save(cliente);                    // pede ao repository para guardar o cliente no banco
    }

    public Cliente atualizar(Long id, Cliente dados) {      // recebe o número do cliente e os dados novos; devolve o cliente atualizado
        Cliente cliente = buscarPorId(id);                  // busca no banco o cliente com esse número (se não existir, dá erro "não encontrado")
        cliente.setNome(dados.getNome());                   // troca o nome antigo pelo nome novo que chegou
        cliente.setEmail(dados.getEmail());                 // troca o e-mail antigo pelo e-mail novo que chegou
        return repository.save(cliente);                    // grava as mudanças no banco e devolve o cliente atualizado
    }

    public void excluir(Long id) {                          // recebe o número do cliente que deve ser apagado; não devolve nada
        Cliente cliente = buscarPorId(id);                  // busca no banco o cliente com esse número (se não existir, dá erro "não encontrado")
        repository.delete(cliente);                         // apaga o cliente do banco
    }


}
