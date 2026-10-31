package com.pds.pedidos.exception;                                          // pasta exception (tratamento de erros)

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice                                                       // vigia todos os controllers: quando algum erro acontece, esta classe é avisada
public class GlobalExceptionHandler {                                       // trata os erros da API num lugar só

    @ExceptionHandler(RecursoNaoEncontradoException.class)                  // este método trata o erro "não encontrado"
    @ResponseStatus(HttpStatus.NOT_FOUND)                                   // responde com o código 404, que quer dizer "não encontrado"
    public ErroDTO tratarNaoEncontrado(RecursoNaoEncontradoException erro) { // recebe o erro que foi disparado
        return new ErroDTO(erro.getMessage());                              // devolve a mensagem do erro, ex.: "Cliente não encontrado: 99"
    }
}