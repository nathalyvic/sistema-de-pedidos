package com.pds.pedidos.exception;

public class RecursoNaoEncontradoException extends RuntimeException {  // aviso de "acabou": herda do RuntimeException, então vira um erro de verdade que pode ser disparado

    public RecursoNaoEncontradoException(String mensagem) {    // para criar o aviso, recebe um texto (ex: "Cliente não encontrado: 99")
        super(mensagem); // entrega o texto para o pai (RuntimeException) guardar
    }
}
