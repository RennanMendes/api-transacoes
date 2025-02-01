package br.com.itau.api_transacoes.api.controller;

import br.com.itau.api_transacoes.api.converter.TransacaoConverter;
import br.com.itau.api_transacoes.api.dto.TransacaoDto;
import br.com.itau.api_transacoes.core.cases.AdicionarTransacaoUseCase;
import br.com.itau.api_transacoes.core.cases.LimparTransacoesUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
@AllArgsConstructor
public class TransacaoController {

    private final AdicionarTransacaoUseCase adicionarTransacao;
    private final LimparTransacoesUseCase limparTransacoes;
    private final TransacaoConverter converter;

    @PostMapping
    public ResponseEntity adicionarTransacao(@Valid @RequestBody TransacaoDto dto) {
        adicionarTransacao.adicionar(converter.dtoToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity deletarTransacoes() {
        limparTransacoes.limpar();
        return ResponseEntity.ok().build();
    }

}