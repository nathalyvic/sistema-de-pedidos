package com.pds.pedidos.controller;                         // este arquivo fica na pasta controller (a porta de entrada da API)

import com.pds.pedidos.domain.Cliente;
import com.pds.pedidos.service.ClienteService;              // usa o service do Cliente
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController                                             // marca esta classe como controller: ela recebe pedidos pela internet (HTTP)
@RequestMapping("/api/clientes")                            // todos os pedidos deste controller começam com o endereço /api/clientes
public class ClienteController {                            // controller do Cliente: recebe os pedidos e passa para o service

    private final ClienteService service;                   // o service que este controller usa

    public ClienteController(ClienteService service) {      // ao criar o controller, o Spring entrega o service
        this.service = service;                             // guarda o service recebido para usar nos métodos
    }

    @GetMapping                                             // atende pedidos GET em /api/clientes
    public List<Cliente> listar() {                         // devolve todos os clientes
        return service.listar();                            // pede ao service a lista de todos os clientes
    }

    @GetMapping("/{id}")                                    // atende pedidos GET em /api/clientes/1, /api/clientes/2...
    public Cliente buscar(@PathVariable Long id) {          // pega o número que veio no endereço e coloca no id
        return service.buscarPorId(id);                     // pede ao service o cliente com esse número
    }

    @PostMapping                                            // atende pedidos POST em /api/clientes (cadastrar)
    @ResponseStatus(HttpStatus.CREATED)                     // responde com o código 201, que quer dizer "criado"
    public Cliente criar(@RequestBody Cliente cliente) {    // pega os dados enviados no pedido (nome e e-mail) e monta um Cliente
        return service.salvar(cliente);                     // pede ao service para salvar e devolve o cliente já com id
    }

    @PutMapping("/{id}")                                    // atende pedidos PUT em /api/clientes/1 (atualizar)
    public Cliente atualizar(@PathVariable Long id, @RequestBody Cliente dados) {   // pega o número do endereço e os dados novos do pedido
        return service.atualizar(id, dados);                // pede ao service para atualizar e devolve o cliente atualizado
    }

    @DeleteMapping("/{id}")                                 // atende pedidos DELETE em /api/clientes/1 (apagar)
    @ResponseStatus(HttpStatus.NO_CONTENT)                  // responde com o código 204, que quer dizer "feito, sem nada para mostrar"
    public void excluir(@PathVariable Long id) {            // pega o número do endereço
        service.excluir(id);                                // pede ao service para apagar o cliente com esse número
    }
}

