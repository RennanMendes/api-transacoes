package br.com.itau.api_transacoes.application.validations;

import br.com.itau.api_transacoes.core.entity.Transacao;
import br.com.itau.api_transacoes.core.exceptions.UnprocessableEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ValidarDataHoraPassadoTest {

    @InjectMocks
    private ValidarDataHoraPassado validarDataHora;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void naoDeveLancarException_quandoDataHoraNoPresente() {
        Transacao transacao = new Transacao(100.00, OffsetDateTime.now());

        assertDoesNotThrow(() -> validarDataHora.validar(transacao));
    }

    @Test
    void naoDeveLancarException_quandoDataHoraNoPassado() {
        Transacao transacao = new Transacao(100.00, OffsetDateTime.now().minusMinutes(2));

        assertDoesNotThrow(() -> validarDataHora.validar(transacao));
    }

    @Test
    void deveLancarException_quandoDataHoraFutura() {
        Transacao transacao = new Transacao(100.00, OffsetDateTime.now().plusMinutes(2));

        UnprocessableEntityException exception = assertThrows(UnprocessableEntityException.class,
                () -> validarDataHora.validar(transacao));

        assertEquals("dataHora", exception.getCampo());
        assertEquals("A dataHora não pode estar no futuro.", exception.getMensagem());
    }

}