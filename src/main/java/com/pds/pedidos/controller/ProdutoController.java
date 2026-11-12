package com.pds.pedidos.controller;                         // pasta controller (a porta de entrada da API)

import com.pds.pedidos.domain.Produto;
import com.pds.pedidos.service.ProdutoService;              // usa o service do Produto
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController                                             // esta classe recebe pedidos pela internet (HTTP)
@RequestMapping("/api/produtos")                            // todos os pedidos deste controller começam com /api/produtos
public class ProdutoController {                            // controller do Produto: recebe os pedidos e passa para o service

    private final ProdutoService service;                   // o service que este controller usa

    public ProdutoController(ProdutoService service) {      // ao criar o controller, o Spring entrega o service
        this.service = service;
    }

    @GetMapping                                             // GET /api/produtos
    public List<Produto> listar() {                         // devolve todos os produtos
        return service.listar();
    }

    @GetMapping("/{id}")                                    // GET /api/produtos/1
    public Produto buscar(@PathVariable Long id) {          // pega o número do endereço e devolve o produto
        return service.buscarPorId(id);
    }

    @PostMapping                                            // POST /api/produtos (cadastrar)
    @ResponseStatus(HttpStatus.CREATED)                     // responde 201, "criado"
    public Produto criar(@RequestBody Produto produto) {    // pega o nome e o preço enviados e monta um Produto
        return service.salvar(produto);                     // salva e devolve o produto já com id
    }

    @PutMapping("/{id}")                                    // PUT /api/produtos/1 (atualizar)
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto dados) { // número do endereço + dados novos
        return service.atualizar(id, dados);                // atualiza e devolve o produto atualizado
    }

    @DeleteMapping("/{id}")                                 // DELETE /api/produtos/1 (apagar)
    @ResponseStatus(HttpStatus.NO_CONTENT)                  // responde 204, "feito, sem nada para mostrar"
    public void excluir(@PathVariable Long id) {
        service.excluir(id);                                // apaga o produto com esse número
    }
}
