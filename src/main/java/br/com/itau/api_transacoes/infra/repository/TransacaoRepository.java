package br.com.itau.api_transacoes.infra.repository;

import br.com.itau.api_transacoes.core.entity.Transacao;

import java.util.List;

public interface TransacaoRepository {
    void adicionarTransacao(Transacao transacao);

    void deletar();

    List<Transacao> buscarTransacoes(Integer intervaloBusca);
}