package br.com.itau.api_transacoes.application.validations;

import br.com.itau.api_transacoes.core.entity.Transacao;
import br.com.itau.api_transacoes.core.exceptions.UnprocessableEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ValidarValorPositivoTest {

    @InjectMocks
    private ValidarValorPositivo valorPositivo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void naoDeveLancarException_quandoValorMaiorQueZero() {
        Transacao transacao = new Transacao(0.01, OffsetDateTime.now());

        assertDoesNotThrow(() -> valorPositivo.validar(transacao));
    }

    @Test
    void deveLancarException_quandoValorForZero() {
        Transacao transacao = new Transacao(0.0, OffsetDateTime.now());

        UnprocessableEntityException exception = assertThrows(UnprocessableEntityException.class,
                () -> valorPositivo.validar(transacao));

        assertEquals("valor", exception.getCampo());
        assertEquals("O valor não pode ser menor ou igual a zero.", exception.getMensagem());
    }

    @Test
    void deveLancarException_quandoValorMenorQueZero() {
        Transacao transacao = new Transacao(-1.0, OffsetDateTime.now());

        UnprocessableEntityException exception = assertThrows(UnprocessableEntityException.class,
                () -> valorPositivo.validar(transacao));

        assertEquals("valor", exception.getCampo());
        assertEquals("O valor não pode ser menor ou igual a zero.", exception.getMensagem());
    }

}