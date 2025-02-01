package br.com.itau.api_transacoes.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transacao {
    private Double valor;
    private OffsetDateTime dataHora;
}
