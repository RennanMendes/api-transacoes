package br.com.itau.api_transacoes.infra.repository;

import br.com.itau.api_transacoes.core.entity.Transacao;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoRepositoryImpl implements TransacaoRepository {

    private List<Transacao> transacoes = new ArrayList<>();

    @Override
    public void adicionarTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }

    @Override
    public void deletar() {
        transacoes.clear();
    }

    @Override
    public List<Transacao> buscarTransacoes(Integer intervaloBusca) {
        OffsetDateTime intervaloDataHora = OffsetDateTime.now().minusSeconds(intervaloBusca);
        return transacoes.stream().filter(transacao -> transacao.getDataHora().isAfter(intervaloDataHora)).toList();
    }

}