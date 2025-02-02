package br.com.itau.api_transacoes.application;

import br.com.itau.api_transacoes.core.cases.ValidarTransacao;
import br.com.itau.api_transacoes.core.entity.Transacao;
import br.com.itau.api_transacoes.core.exceptions.BadRequestException;
import br.com.itau.api_transacoes.core.exceptions.UnprocessableEntityException;
import br.com.itau.api_transacoes.infra.repository.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AdicionarTransacaoUseCaseImplTest {

    @InjectMocks
    private AdicionarTransacaoUseCaseImpl adicionarTransacao;

    @Mock
    private TransacaoRepository repository;

    @Mock
    private List<ValidarTransacao> validadores;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAdicionarTransacao_quandoTransacaoValida() {
        Transacao transacao = new Transacao(100.00, OffsetDateTime.now());

        doNothing().when(validadores).forEach(any(Consumer.class));
        doNothing().when(repository).adicionarTransacao(transacao);

        adicionarTransacao.adicionar(transacao);

        verify(validadores).forEach(any(Consumer.class));
        verify(repository).adicionarTransacao(transacao);
        verifyNoMoreInteractions(validadores, repository);
    }

    @Test
    void deveRetornarBadRequest_quandoTransacaoNula() {
        String campo = "valor";
        String mensagem = "O campo valor é obrigatório.";
        Transacao transacao = new Transacao(null, null);
        BadRequestException exception = new BadRequestException(campo, mensagem);

        doThrow(exception).when(validadores).forEach(any(Consumer.class));

        BadRequestException resposta = assertThrows(BadRequestException.class,
                () -> adicionarTransacao.adicionar(transacao)
        );

        verify(validadores).forEach(any(Consumer.class));
        verifyNoMoreInteractions(validadores);
        verifyNoInteractions(repository);

        assertEquals(campo, resposta.getCampo());
        assertEquals(mensagem, resposta.getMensagem());
    }

    @Test
    void deveRetornarUnprocessableEntity_quandoTransacaoNaoAtenderACriteriosDeAceite() {
        String campo = "dataHora";
        String mensagem = "A dataHora não pode estar no futuro.";
        Transacao transacao = new Transacao(200.00, OffsetDateTime.now().plusMinutes(2));
        UnprocessableEntityException exception = new UnprocessableEntityException(campo, mensagem);

        doThrow(exception).when(validadores).forEach(any(Consumer.class));

        UnprocessableEntityException resposta = assertThrows(UnprocessableEntityException.class,
                () -> adicionarTransacao.adicionar(transacao)
        );

        verify(validadores).forEach(any(Consumer.class));
        verifyNoMoreInteractions(validadores);
        verifyNoInteractions(repository);

        assertEquals(campo, resposta.getCampo());
        assertEquals(mensagem, resposta.getMensagem());
    }

}