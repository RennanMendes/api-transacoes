package br.com.itau.api_transacoes.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    public BadRequestException(String campo, String message) {
        super(String.format("Erro no campo '%s': %s", campo, message));
    }
}
