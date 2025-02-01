package br.com.itau.api_transacoes.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Estatistica {
    private Long count;
    private Double sum;
    private Double avg;
    private Double min;
    private Double max;
}