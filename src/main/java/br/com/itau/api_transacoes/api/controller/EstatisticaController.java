package br.com.itau.api_transacoes.api.controller;

import br.com.itau.api_transacoes.api.dto.EstatisticaDto;
import br.com.itau.api_transacoes.core.cases.CalcularEstatisticaUseCase;
import br.com.itau.api_transacoes.core.entity.Estatistica;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
@AllArgsConstructor
public class EstatisticaController {

    private final CalcularEstatisticaUseCase calcularEstatistica;

    @GetMapping
    public ResponseEntity<EstatisticaDto> calcularEstatistica(@RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBuscas) {
        Estatistica estatistica = calcularEstatistica.calcular(intervaloBuscas);
        return ResponseEntity.ok().body(new EstatisticaDto(estatistica));
    }
}