package br.com.itau.api_transacoes.api.converter;

import br.com.itau.api_transacoes.api.dto.TransacaoDto;
import br.com.itau.api_transacoes.core.entity.Transacao;
import org.springframework.stereotype.Component;

@Component
public class TransacaoConverter implements DtoConverter<Transacao, TransacaoDto>{

    @Override
    public Transacao dtoToEntity(TransacaoDto dto) {
        return new Transacao(dto.valor(), dto.dataHora());
    }
}
