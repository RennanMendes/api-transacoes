package br.com.itau.api_transacoes.core.exceptions;

import lombok.Getter;

@Getter
public class UnprocessableEntityException extends RuntimeException {

    private final String campo;
    private final String mensagem;

    public UnprocessableEntityException(String campo, String mensagem) {
        super(String.format("Erro no campo '%s': %s", campo, mensagem));
        this.campo = campo;
        this.mensagem = mensagem;
    }
}