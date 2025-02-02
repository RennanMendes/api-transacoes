package br.com.itau.api_transacoes.application.validations;

import br.com.itau.api_transacoes.core.entity.Transacao;
import br.com.itau.api_transacoes.core.exceptions.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ValidarValorNaoNullTest {

    @InjectMocks
    private ValidarValorNaoNull valorNaoNull;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void naoDeveLancarException_quandoValorValido() {
        Transacao transacao = new Transacao(100.00, OffsetDateTime.now());

        assertDoesNotThrow(() -> valorNaoNull.validar(transacao));
    }

    @Test
    void deveLancarException_quandoValorNulo() {
        Transacao transacao = new Transacao(null, OffsetDateTime.now());

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> valorNaoNull.validar(transacao));

        assertEquals("valor", exception.getCampo());
        assertEquals("O campo valor é obrigatório.", exception.getMensagem());
    }
}