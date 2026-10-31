package com.pds.pedidos.exception;                          // pasta exception (tratamento de erros)

public record ErroDTO(String mensagem) {                    // formato da resposta de erro: só uma mensagem de texto
}