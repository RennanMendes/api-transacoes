package br.com.itau.api_transacoes.application.validations;

import br.com.itau.api_transacoes.core.cases.ValidarTransacao;
import br.com.itau.api_transacoes.core.entity.Transacao;
import br.com.itau.api_transacoes.core.exceptions.UnprocessableEntityException;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class ValidarDataHoraPassado implements ValidarTransacao {

    @Override
    public void validar(Transacao transacao) {
        if (transacao.getDataHora().isAfter(OffsetDateTime.now())) {
            throw new UnprocessableEntityException("dataHora", "A dataHora não pode estar no futuro.");
        }
    }
}