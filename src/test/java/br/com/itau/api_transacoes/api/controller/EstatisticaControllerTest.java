package br.com.itau.api_transacoes.api.controller;

import br.com.itau.api_transacoes.core.cases.CalcularEstatisticaUseCase;
import br.com.itau.api_transacoes.core.entity.Estatistica;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class EstatisticaControllerTest {

    private static final String BASE_URL = "/estatistica";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CalcularEstatisticaUseCase calcularEstatistica;

    @Test
    void calcularEstatistica_deveRetornar200ComComIntervaloPadrao() throws Exception {
        Estatistica estatistica = new Estatistica(2L, 100.0, 50.0,50.0,50.0);

        when(calcularEstatistica.calcular(60)).thenReturn(estatistica);

        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(2L))
                .andExpect(jsonPath("$.sum").value(100))
                .andExpect(jsonPath("$.avg").value(50))
                .andExpect(jsonPath("$.min").value(50))
                .andExpect(jsonPath("$.max").value(50));

        verify(calcularEstatistica).calcular(60);
        verifyNoMoreInteractions(calcularEstatistica);
    }

    @Test
    void calcularEstatistica_deveRetornar200ComEstatisticasComIntervaloCustomizado() throws Exception {
        Estatistica estatistica = new Estatistica(2L, 100.0, 50.0,50.0,50.0);

        when(calcularEstatistica.calcular(120)).thenReturn(estatistica);

        mockMvc.perform(get(BASE_URL)
                        .param("intervaloBusca", "120"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(2L))
                .andExpect(jsonPath("$.sum").value(100))
                .andExpect(jsonPath("$.avg").value(50))
                .andExpect(jsonPath("$.min").value(50))
                .andExpect(jsonPath("$.max").value(50));

        verify(calcularEstatistica).calcular(120);
        verifyNoMoreInteractions(calcularEstatistica);
    }

}