package br.com.itau.api_transacoes.application;

import br.com.itau.api_transacoes.infra.repository.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

class LimparTransacoesUseCaseImplTest {

    @InjectMocks
    private LimparTransacoesUseCaseImpl limparTransacoesUseCase;

    @Mock
    private TransacaoRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveChamarMetodoDeletarDoRepositorio() {
        limparTransacoesUseCase.limpar();

        verify(repository).deletar();
        verifyNoMoreInteractions(repository);
    }

}