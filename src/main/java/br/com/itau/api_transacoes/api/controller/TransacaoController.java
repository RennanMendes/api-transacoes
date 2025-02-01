package br.com.itau.api_transacoes.api.controller;

import br.com.itau.api_transacoes.api.converter.TransacaoConverter;
import br.com.itau.api_transacoes.api.dto.TransacaoDto;
import br.com.itau.api_transacoes.core.cases.AdicionarTransacaoUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final AdicionarTransacaoUseCase adicionarTransacao;
    private final TransacaoConverter converter;

    @Autowired
    public TransacaoController(AdicionarTransacaoUseCase adicionarTransacao, TransacaoConverter converter) {
        this.adicionarTransacao = adicionarTransacao;
        this.converter = converter;
    }

    @PostMapping
    public ResponseEntity adicionarTransacao(@Valid @RequestBody TransacaoDto dto) {
        adicionarTransacao.adicionar(converter.dtoToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
