package br.com.itau.api_transacoes.api.dto;

import java.time.OffsetDateTime;

public record TransacaoDto(
        Double valor,
        OffsetDateTime dataHora
) {
}
