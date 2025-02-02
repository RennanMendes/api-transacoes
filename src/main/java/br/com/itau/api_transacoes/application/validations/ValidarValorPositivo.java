package br.com.itau.api_transacoes.application.validations;

import br.com.itau.api_transacoes.core.cases.ValidarTransacao;
import br.com.itau.api_transacoes.core.entity.Transacao;
import br.com.itau.api_transacoes.core.exceptions.UnprocessableEntityException;
import org.springframework.stereotype.Component;

@Component
public class ValidarValorPositivo implements ValidarTransacao {

    @Override
    public void validar(Transacao transacao) {
        if (transacao.getValor() <= 0) {
            throw new UnprocessableEntityException("valor", "O valor não pode ser menor ou igual a zero.");
        }
    }
}
