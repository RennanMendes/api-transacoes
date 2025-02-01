package br.com.itau.api_transacoes.application;

import br.com.itau.api_transacoes.core.cases.CalcularEstatisticaUseCase;
import br.com.itau.api_transacoes.core.entity.Estatistica;
import br.com.itau.api_transacoes.core.entity.Transacao;
import br.com.itau.api_transacoes.infra.repository.TransacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@AllArgsConstructor
public class CalcularEstatisticaUseCaseImpl implements CalcularEstatisticaUseCase {

    private TransacaoRepository repository;

    @Override
    public Estatistica calcular(Integer intervaloBusca) {
        List<Transacao> transacoes = repository.buscarTransacoes(intervaloBusca);

        if (transacoes.isEmpty()) {
            return new Estatistica(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics estatistica = transacoes.stream().mapToDouble(Transacao::getValor).summaryStatistics();
        return new Estatistica(estatistica.getCount(), estatistica.getSum(), estatistica.getAverage(), estatistica.getMin(), estatistica.getMax());
    }

}