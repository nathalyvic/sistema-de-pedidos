package com.pds.pedidos.repository;                         // pasta repository (o estoquista)

import com.pds.pedidos.domain.Produto;                      // traz o Produto que o estoquista vai guardar
import org.springframework.data.jpa.repository.JpaRepository; // traz o estoquista pronto do Spring

public interface ProdutoRepository                          // ESTOQUISTA do Produto: guarda e busca produtos no banco
        extends JpaRepository<Produto, Long> {              // herda do estoquista pronto: já sabe salvar, buscar, listar e apagar

}

// <Produto, Long> = guarda Produtos, e o id de cada um é do tipo Long
// bloco vazio de propósito: o Spring escreve sozinho todo o código do banco
