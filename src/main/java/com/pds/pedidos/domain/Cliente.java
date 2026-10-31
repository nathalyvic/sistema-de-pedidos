package com.pds.pedidos.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity     // Transforma a classe Cliente numa tabela do banco (a despensa).
            // Sem isso, o cliente não seria salvo em lugar nenhum.

public class Cliente {
    @Id     // avisa o banco que o id é o numero de identificação de cada cliente
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O id é gerado automaticamente, e quem gera é o próprio banco, contando um por um.”
    private Long id;    // id do cliente
    private String nome;
    private String email;

    public Long getId() {   // pega o id no banco
        return id;  // devolve o valor quardado no campo id
    }

    public void setId(Long id) {    // metodo void pq nao retorna nada, apenas altera | “set” = definir, (String nome) Recebe um texto
        this.id = id;   // Guarda o nome novo no campo da classe.
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
}
