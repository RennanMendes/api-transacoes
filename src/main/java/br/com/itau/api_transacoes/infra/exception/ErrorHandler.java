package br.com.itau.api_transacoes.infra.exception;

import br.com.itau.api_transacoes.core.exceptions.BadRequestException;
import br.com.itau.api_transacoes.core.exceptions.UnprocessableEntityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity handleError400(BadRequestException exception) {
        log.error("Requisição inválida recebida - campo: {}, mensagem: {}", exception.getCampo(), exception.getMensagem());
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity handleError422(UnprocessableEntityException exception) {
        log.error("Requisição inválida recebida - campo: {}, mensagem: {}", exception.getCampo(), exception.getMensagem());
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleError422(HttpMessageNotReadableException exception) {
        log.error("Requisição inválida recebida - mensagem: {}", exception.getMessage());
        return ResponseEntity.badRequest().build();
    }
}