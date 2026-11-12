package com.pds.pedidos.domain;                             // pasta domain (as entidades do sistema)

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;                                // BigDecimal: tipo certo para dinheiro (não perde centavos como o double)

@Entity                                                     // transforma a classe Produto numa tabela do banco
public class Produto {

    @Id                                                     // o id é o número de identificação de cada produto
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // o próprio banco gera o id, contando um por um
    private Long id;                                        // id do produto
    private String nome;                                    // nome do produto, ex.: "Caneta"
    private BigDecimal preco;                               // preço do produto, ex.: 3.50

    public Long getId() {                                   // devolve o id do produto
        return id;
    }

    public void setId(Long id) {                            // troca o id do produto
        this.id = id;
    }

    public String getNome() {                               // devolve o nome do produto
        return nome;
    }

    public void setNome(String nome) {                      // troca o nome do produto
        this.nome = nome;
    }

    public BigDecimal getPreco() {                          // devolve o preço do produto
        return preco;
    }

    public void setPreco(BigDecimal preco) {                // troca o preço do produto
        this.preco = preco;
    }
}
