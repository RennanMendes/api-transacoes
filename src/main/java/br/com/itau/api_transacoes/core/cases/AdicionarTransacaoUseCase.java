package br.com.itau.api_transacoes.core.cases;

import br.com.itau.api_transacoes.core.entity.Transacao;

public interface AdicionarTransacaoUseCase {
    void adicionar(Transacao transacao);
}
