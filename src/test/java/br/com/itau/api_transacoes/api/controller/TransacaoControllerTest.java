package br.com.itau.api_transacoes.api.controller;

import br.com.itau.api_transacoes.api.converter.TransacaoConverter;
import br.com.itau.api_transacoes.api.dto.TransacaoDto;
import br.com.itau.api_transacoes.core.cases.AdicionarTransacaoUseCase;
import br.com.itau.api_transacoes.core.cases.LimparTransacoesUseCase;
import br.com.itau.api_transacoes.core.entity.Transacao;
import br.com.itau.api_transacoes.core.exceptions.BadRequestException;
import br.com.itau.api_transacoes.core.exceptions.UnprocessableEntityException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class TransacaoControllerTest {

    private static final String BASE_URL = "/transacao";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AdicionarTransacaoUseCase adicionarTransacao;

    @MockitoBean
    private LimparTransacoesUseCase limparTransacoes;

    @MockitoBean
    private TransacaoConverter converter;

    @Test
    void adicionarTransacao_deveRetornar201() throws Exception {
        OffsetDateTime dataHora = OffsetDateTime.now(ZoneOffset.UTC);
        TransacaoDto dto = new TransacaoDto(100.0, dataHora);
        Transacao transacao = new Transacao(100.0, dataHora);

        when(converter.dtoToEntity(dto)).thenReturn(transacao);
        doNothing().when(adicionarTransacao).adicionar(transacao);

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(content().string(""));

        verify(converter).dtoToEntity(dto);
        verify(adicionarTransacao).adicionar(transacao);
        verifyNoMoreInteractions(converter, adicionarTransacao);
        verifyNoInteractions(limparTransacoes);
    }

    @Test
    void adicionarTransacao_deveRetornarBadRequest() throws Exception {
        TransacaoDto dto = new TransacaoDto(null, null);
        Transacao transacao = new Transacao();
        BadRequestException exception = new BadRequestException("valor", "O campo valor é obrigatório.");

        when(converter.dtoToEntity(dto)).thenReturn(transacao);
        doThrow(exception).when(adicionarTransacao).adicionar(transacao);

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""));

        verify(converter).dtoToEntity(dto);
        verify(adicionarTransacao).adicionar(transacao);
        verifyNoMoreInteractions(converter, adicionarTransacao);
        verifyNoInteractions(limparTransacoes);
    }

    @Test
    void adicionarTransacao_deveRetornarUnprocessableEntity() throws Exception {
        OffsetDateTime dataHora = OffsetDateTime.now(ZoneOffset.UTC).plusMinutes(2);
        TransacaoDto dto = new TransacaoDto(100.0, dataHora);
        Transacao transacao = new Transacao(100.0, dataHora);

        UnprocessableEntityException exception = new UnprocessableEntityException("dataHora", "A dataHora não pode estar no futuro.");

        when(converter.dtoToEntity(dto)).thenReturn(transacao);
        doThrow(exception).when(adicionarTransacao).adicionar(transacao);

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(""));

        verify(converter).dtoToEntity(dto);
        verify(adicionarTransacao).adicionar(transacao);
        verifyNoMoreInteractions(converter, adicionarTransacao);
        verifyNoInteractions(limparTransacoes);
    }

    @Test
    void adicionarTransacao_comXmlDeveRetornarUnsupportedMediaType() throws Exception {
        String xmlContent = "<TransacaoDto><valor>100.0</valor><dataHora>2024-02-02T12:00:00Z</dataHora></TransacaoDto>";

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_XML)
                        .content(xmlContent))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void deletarTransacoes_deveRetornar200() throws Exception {
        mockMvc.perform(delete(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(limparTransacoes).limpar();
        verifyNoMoreInteractions(limparTransacoes);
        verifyNoInteractions(converter, adicionarTransacao);
    }

}