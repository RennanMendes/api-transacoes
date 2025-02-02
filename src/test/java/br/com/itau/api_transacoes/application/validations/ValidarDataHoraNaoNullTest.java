package br.com.itau.api_transacoes.application.validations;

import br.com.itau.api_transacoes.core.entity.Transacao;
import br.com.itau.api_transacoes.core.exceptions.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ValidarDataHoraNaoNullTest {

    @InjectMocks
    private ValidarDataHoraNaoNull validarDataHoraNaoNull;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void naoDeveLancarException_quandoHoraValida() {
        Transacao transacao = new Transacao(100.00, OffsetDateTime.now());

        assertDoesNotThrow(() -> validarDataHoraNaoNull.validar(transacao));
    }

    @Test
    void deveLancarException_quandoHoraNula() {
        Transacao transacao = new Transacao(100.00, null);

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> validarDataHoraNaoNull.validar(transacao));

        assertEquals("dataHora", exception.getCampo());
        assertEquals("O campo dataHora é obrigatório.", exception.getMensagem());
    }
}