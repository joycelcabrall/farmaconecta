package br.com.farmaconecta.interfaces.rest.exception;

import br.com.farmaconecta.domain.exception.RegraDeNegocioException;
import br.com.farmaconecta.domain.exception.RecursoNaoEncontradoException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 - RECURSO NÃO ENCONTRADO

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> tratarRecursoNaoEncontrado(
            RecursoNaoEncontradoException excecao
    ) {

        Map<String, Object> resposta = Map.of(
                "status", HttpStatus.NOT_FOUND.value(),
                "erro", "Recurso não encontrado",
                "mensagem", excecao.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(resposta);
    }

    // 409 - CONFLITO DE REGRA DE NEGÓCIO

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<Map<String, Object>> tratarRegraDeNegocio(
            RegraDeNegocioException excecao
    ) {

        Map<String, Object> resposta = Map.of(
                "status", HttpStatus.CONFLICT.value(),
                "erro", "Conflito de regra de negócio",
                "mensagem", excecao.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(resposta);
    }

    // 400 - DADOS INVÁLIDOS ENVIADOS NA REQUISIÇÃO

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarDadosInvalidos(
            MethodArgumentNotValidException excecao
    ) {

        String mensagem = excecao.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .findFirst()
                .orElse("Dados inválidos.");

        Map<String, Object> resposta = Map.of(
                "status", HttpStatus.BAD_REQUEST.value(),
                "erro", "Dados inválidos",
                "mensagem", mensagem
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(resposta);
    }
}