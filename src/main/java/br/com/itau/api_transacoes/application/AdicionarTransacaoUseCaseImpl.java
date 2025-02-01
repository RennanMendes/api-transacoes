package br.com.itau.api_transacoes.application;

import br.com.itau.api_transacoes.core.cases.AdicionarTransacaoUseCase;
import br.com.itau.api_transacoes.core.cases.ValidarTransacao;
import br.com.itau.api_transacoes.core.entity.Transacao;
import br.com.itau.api_transacoes.infra.repository.TransacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdicionarTransacaoUseCaseImpl implements AdicionarTransacaoUseCase {

    private final TransacaoRepository repository;
    private final List<ValidarTransacao> validadores;

    @Override
    public void adicionar(Transacao transacao) {
        validadores.forEach(v -> v.validar(transacao));
        repository.adicionarTransacao(transacao);
    }
}