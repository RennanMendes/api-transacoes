package br.com.itau.api_transacoes.application;

import br.com.itau.api_transacoes.core.cases.LimparTransacoesUseCase;
import br.com.itau.api_transacoes.infra.repository.TransacaoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class LimparTransacoesUseCaseImpl implements LimparTransacoesUseCase {

    private final TransacaoRepository repository;

    @Override
    public void limpar() {
        log.info("Limpando histórico de transações.");
        repository.deletar();
    }
}