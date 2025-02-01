package br.com.itau.api_transacoes.api.converter;

public interface DtoConverter<A, B> {

    default A dtoToEntity(B persistenceObject) {
        throw new UnsupportedOperationException();
    }
}
