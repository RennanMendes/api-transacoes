package br.com.itau.api_transacoes.application;

import br.com.itau.api_transacoes.core.entity.Estatistica;
import br.com.itau.api_transacoes.core.entity.Transacao;
import br.com.itau.api_transacoes.infra.repository.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalcularEstatisticaUseCaseImplTest {

    @InjectMocks
    private CalcularEstatisticaUseCaseImpl calcularEstatisticaUseCase;

    @Mock
    private TransacaoRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarEstatisticaComValoresCalculados_quandoExistiremTransacoes() {
        List<Transacao> transacoes = List.of(
                new Transacao(100.0, OffsetDateTime.now().minusSeconds(10)),
                new Transacao(200.0, OffsetDateTime.now().minusSeconds(20)),
                new Transacao(300.0, OffsetDateTime.now().minusSeconds(30))
        );

        when(repository.buscarTransacoes(60)).thenReturn(transacoes);

        Estatistica estatistica = calcularEstatisticaUseCase.calcular(60);

        verify(repository).buscarTransacoes(60);
        verifyNoMoreInteractions(repository);

        assertEquals(3, estatistica.getCount());
        assertEquals(600.0, estatistica.getSum());
        assertEquals(200.0, estatistica.getAvg());
        assertEquals(100.0, estatistica.getMin());
        assertEquals(300.0, estatistica.getMax());
    }

    @Test
    void deveRetornarEstatisticaZerada_quandoNaoExistiremTransacoes() {
        List<Transacao> transacoes = new ArrayList<>();

        when(repository.buscarTransacoes(60)).thenReturn(transacoes);

        Estatistica estatistica = calcularEstatisticaUseCase.calcular(60);

        verify(repository).buscarTransacoes(60);
        verifyNoMoreInteractions(repository);

        assertEquals(0L, estatistica.getCount());
        assertEquals(0.0, estatistica.getSum());
        assertEquals(0.0, estatistica.getAvg());
        assertEquals(0.0, estatistica.getMin());
        assertEquals(0.0, estatistica.getMax());
    }

}