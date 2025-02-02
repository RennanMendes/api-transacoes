package br.com.itau.api_transacoes.core.exceptions;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {
    private final String campo;
    private final String mensagem;

    public BadRequestException(String campo, String mensagem) {
        super(String.format("Erro no campo '%s': %s", campo, mensagem));
        this.campo = campo;
        this.mensagem = mensagem;
    }
}