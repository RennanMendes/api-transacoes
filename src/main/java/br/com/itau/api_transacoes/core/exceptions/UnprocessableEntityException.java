package br.com.itau.api_transacoes.core.exceptions;

public class UnprocessableEntityException extends RuntimeException {
    public UnprocessableEntityException(String campo, String message) {
        super(String.format("Erro no campo '%s': %s", campo, message));
    }
}