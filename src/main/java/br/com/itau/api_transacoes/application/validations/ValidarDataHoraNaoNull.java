package br.com.itau.api_transacoes.application.validations;

import br.com.itau.api_transacoes.core.cases.ValidarTransacao;
import br.com.itau.api_transacoes.core.entity.Transacao;
import br.com.itau.api_transacoes.core.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class ValidarDataHoraNaoNull implements ValidarTransacao {

    @Override
    public void validar(Transacao transacao) {
        if (transacao.getDataHora() == null) {
            throw new BadRequestException("dataHora", "O campo dataHora é obrigatório.");
        }
    }
}
