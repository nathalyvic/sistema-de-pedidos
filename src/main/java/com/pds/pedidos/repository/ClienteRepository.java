package com.pds.pedidos.repository;
// pasta onde este arquivo fica: repository (o estoquista)

import com.pds.pedidos.domain.Cliente;
// traz o Cliente que o estoquista vai guardar

import org.springframework.data.jpa.repository.JpaRepository;
// traz o estoquista pronto do Spring

public interface ClienteRepository   // ESTOQUISTA do Cliente: guarda e busca clientes no banco (despensa)
extends JpaRepository<Cliente, Long> {  // herda do estoquista pronto: já sabe salvar, buscar, listar e apagar

}

// <Cliente, Long> = guarda Clientes, e o id de cada um é do tipo Long
// bloco vazio de propósito: o Spring escreve sozinho todo o código do banco