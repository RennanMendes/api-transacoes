package br.com.itau.api_transacoes.core.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String campo, String message) {
        super(String.format("Erro no campo '%s': %s", campo, message));
    }
}