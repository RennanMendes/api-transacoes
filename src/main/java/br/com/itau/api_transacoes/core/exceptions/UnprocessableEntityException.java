package br.com.itau.api_transacoes.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends RuntimeException {
    public UnprocessableEntityException(String campo, String message) {
        super(String.format("Erro no campo '%s': %s", campo, message));
    }
}
