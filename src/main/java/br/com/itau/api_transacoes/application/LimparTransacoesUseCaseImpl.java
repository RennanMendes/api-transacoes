package br.com.itau.api_transacoes.application;

import br.com.itau.api_transacoes.core.cases.LimparTransacoesUseCase;
import br.com.itau.api_transacoes.infra.repository.TransacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LimparTransacoesUseCaseImpl implements LimparTransacoesUseCase {

    private final TransacaoRepository repository;

    @Override
    public void limpar() {
        repository.deletar();
    }
}
