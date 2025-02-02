package br.com.itau.api_transacoes.infra.repository;

import br.com.itau.api_transacoes.core.entity.Transacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransacaoRepositoryImplTest {

    @InjectMocks
    private TransacaoRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAdicionarTransacao() {
        Transacao transacao = new Transacao(150.0, OffsetDateTime.now());

        repository.adicionarTransacao(transacao);
        List<Transacao> transacoes = repository.buscarTransacoes(60);

        assertEquals(1, transacoes.size());
        assertEquals(transacao, transacoes.get(0));
    }

    @Test
    void deveDeletarTodasAsTransacoes() {
        repository.adicionarTransacao(new Transacao(100.0, OffsetDateTime.now()));
        repository.adicionarTransacao(new Transacao(200.0, OffsetDateTime.now()));

        repository.deletar();

        List<Transacao> transacoes = repository.buscarTransacoes(60);
        assertTrue(transacoes.isEmpty());
    }

    @Test
    void deveBuscarTransacoesDentroDoIntervalo() {
        Transacao transacaoRecente = new Transacao(50.0, OffsetDateTime.now().minusSeconds(30));
        Transacao transacaoForaDoIntervalo = new Transacao(75.0, OffsetDateTime.now().minusSeconds(120));

        repository.adicionarTransacao(transacaoRecente);
        repository.adicionarTransacao(transacaoForaDoIntervalo);

        List<Transacao> transacoes = repository.buscarTransacoes(60);

        assertEquals(1, transacoes.size());
        assertEquals(transacaoRecente, transacoes.get(0));
    }

    @Test
    void deveRetornarListaVazia_quandoNaoExistiremTransacoesDentroDoIntervalo() {
        repository.adicionarTransacao(new Transacao(150.0, OffsetDateTime.now().minusSeconds(200)));

        List<Transacao> transacoes = repository.buscarTransacoes(60);

        assertTrue(transacoes.isEmpty());
    }

}