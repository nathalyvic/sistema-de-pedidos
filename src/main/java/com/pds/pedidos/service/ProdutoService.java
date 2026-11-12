package com.pds.pedidos.service;                            // pasta service (as regras do sistema)

import com.pds.pedidos.domain.Produto;
import com.pds.pedidos.exception.RecursoNaoEncontradoException;
import com.pds.pedidos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service                                                    // marca esta classe como service: o Spring cria e entrega ela para quem precisar
public class ProdutoService {
    private final ProdutoRepository repository;             // o repository que este service usa para falar com o banco

    public ProdutoService(ProdutoRepository repository) {   // ao criar o service, o Spring entrega o repository
        this.repository = repository;                       // guarda o repository recebido para usar nos métodos
    }

    public List<Produto> listar() {                         // devolve a lista de todos os produtos
        return repository.findAll();                        // pede ao repository para buscar todos os produtos no banco
    }

    public Produto buscarPorId(Long id) {                   // devolve o produto com esse número
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado: " + id)); // se não encontrar, dispara o erro "não encontrado" (vira 404)
    }

    public Produto salvar(Produto produto) {                // recebe um produto novo e devolve ele salvo, já com id
        return repository.save(produto);                    // pede ao repository para guardar o produto no banco
    }

    public Produto atualizar(Long id, Produto dados) {      // recebe o número do produto e os dados novos; devolve o produto atualizado
        Produto produto = buscarPorId(id);                  // busca o produto (se não existir, dá erro "não encontrado")
        produto.setNome(dados.getNome());                   // troca o nome antigo pelo nome novo
        produto.setPreco(dados.getPreco());                 // troca o preço antigo pelo preço novo
        return repository.save(produto);                    // grava as mudanças no banco e devolve o produto atualizado
    }

    public void excluir(Long id) {                          // recebe o número do produto que deve ser apagado
        Produto produto = buscarPorId(id);                  // busca o produto (se não existir, dá erro "não encontrado")
        repository.delete(produto);                         // apaga o produto do banco
    }
}
