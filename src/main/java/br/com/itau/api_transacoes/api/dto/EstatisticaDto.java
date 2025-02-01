package br.com.itau.api_transacoes.api.dto;

import br.com.itau.api_transacoes.core.entity.Estatistica;

public record EstatisticaDto(
        Long count,
        Double sum,
        Double avg,
        Double min,
        Double max
) {

    public EstatisticaDto(Estatistica estatistica) {
        this(
                estatistica.getCount(),
                estatistica.getSum(),
                estatistica.getAvg(),
                estatistica.getMin(),
                estatistica.getMax()
        );
    }
}