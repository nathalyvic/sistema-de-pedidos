package com.pds.pedidos.service;                            // mesma pasta do PedidoService, só que dentro de src/test

import com.pds.pedidos.domain.Pedido;
import com.pds.pedidos.exception.RecursoNaoEncontradoException;
import com.pds.pedidos.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;   // confere se dois valores são iguais
import static org.junit.jupiter.api.Assertions.assertThrows;   // confere se um erro foi disparado
import static org.mockito.Mockito.when;                        // "quando chamarem tal método, devolva tal coisa"

@ExtendWith(MockitoExtension.class)                         // liga o Mockito: ele cria os objetos falsos (mocks) do teste
class PedidoServiceTest {

    @Mock                                                   // repository FALSO: não usa banco, só responde o que a gente mandar
    private PedidoRepository repository;

    @Mock                                                   // service do Cliente falso (o PedidoService precisa dele para ser criado)
    private ClienteService clienteService;

    @Mock                                                   // service do Produto falso (o PedidoService precisa dele para ser criado)
    private ProdutoService produtoService;

    @InjectMocks                                            // cria o PedidoService DE VERDADE, entregando os falsos acima no construtor
    private PedidoService service;

    @Test
    void deveRetornarPedidoQuandoExiste() {
        Pedido pedido = new Pedido();                       // monta um pedido de mentira
        pedido.setId(1L);                                   // com id 1

        when(repository.findById(1L)).thenReturn(Optional.of(pedido)); // quando buscarem o id 1 no banco, devolve esse pedido

        Pedido resultado = service.buscarPorId(1L);         // chama o método que está sendo testado

        assertEquals(1L, resultado.getId());                // confere se veio o pedido de id 1
    }

    @Test
    void deveLancarExcecaoQuandoPedidoNaoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty()); // quando buscarem o id 99, o banco não acha nada

        assertThrows(RecursoNaoEncontradoException.class,   // confere se o erro "não encontrado" é disparado
                () -> service.buscarPorId(99L));            // ao buscar o pedido 99
    }
}
