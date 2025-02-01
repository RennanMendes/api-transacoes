package br.com.itau.api_transacoes.core.cases;

import br.com.itau.api_transacoes.core.entity.Estatistica;

public interface CalcularEstatisticaUseCase {

    Estatistica calcular(Integer intervaloBuscas);
}