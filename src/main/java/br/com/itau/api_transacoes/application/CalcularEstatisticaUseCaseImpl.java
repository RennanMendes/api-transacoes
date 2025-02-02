package br.com.itau.api_transacoes.application;

import br.com.itau.api_transacoes.core.cases.CalcularEstatisticaUseCase;
import br.com.itau.api_transacoes.core.entity.Estatistica;
import br.com.itau.api_transacoes.core.entity.Transacao;
import br.com.itau.api_transacoes.infra.repository.TransacaoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CalcularEstatisticaUseCaseImpl implements CalcularEstatisticaUseCase {

    private TransacaoRepository repository;

    @Override
    public Estatistica calcular(Integer intervaloBusca) {
        log.info("Iniciando busca de transações pelo intervalo de {} segundos.", intervaloBusca);
        List<Transacao> transacoes = repository.buscarTransacoes(intervaloBusca);

        if (transacoes.isEmpty()) {
            return new Estatistica(0L, 0.0, 0.0, 0.0, 0.0);
        }

        log.info("Gerando estatísticas.");
        DoubleSummaryStatistics estatistica = transacoes.stream().mapToDouble(Transacao::getValor).summaryStatistics();
        return new Estatistica(estatistica.getCount(), estatistica.getSum(), estatistica.getAverage(), estatistica.getMin(), estatistica.getMax());
    }

}