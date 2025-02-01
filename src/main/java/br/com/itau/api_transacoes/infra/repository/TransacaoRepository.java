package br.com.itau.api_transacoes.infra.repository;

import br.com.itau.api_transacoes.core.entity.Transacao;

public interface TransacaoRepository {
    void adicionarTransacao(Transacao transacao);
}
