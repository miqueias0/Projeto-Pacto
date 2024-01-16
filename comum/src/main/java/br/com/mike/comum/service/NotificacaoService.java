package br.com.mike.comum.service;

import br.com.mike.comum.interceptor.Inteceptor;
import br.com.mike.comum.records.CandidaturaRecord;
import br.com.mike.comum.records.MensagemRetorno;
import br.com.mike.comum.records.NotificacaoRecord;
import br.com.mike.comum.util.CarregarProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.json.internal.json_simple.parser.JSONParser;
import org.jose4j.lang.ByteUtil;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class NotificacaoService {

    private RestTemplate restTemplate;

    private String CAMINHO;

    private HttpHeaders sec;
    public NotificacaoService(HttpHeaders sec) throws Exception{
        restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(new Inteceptor(sec)));
        CarregarProperties properties = new CarregarProperties("comum.properties");
        CAMINHO = properties.getProperties().getProperty("notificacao");
        this.sec = sec;
    }

    public List<NotificacaoRecord> obterListaPorVagaId(String vagaId){
        ResponseEntity<List<NotificacaoRecord>> responseEntity = restTemplate.exchange(
                CAMINHO + "/obterListaPorVagaId?vagaId=" + vagaId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<NotificacaoRecord>>() {}
        );
        return responseEntity.getBody();
    }

    public String manter(CandidaturaRecord candidaturaRecord) throws Exception {
        sec.remove("content-length");
        ResponseEntity<MensagemRetorno> responseEntity = restTemplate.postForEntity(
                CAMINHO + "/manter",
                new HttpEntity<>(candidaturaRecord, sec),
                MensagemRetorno.class
        );
        return responseEntity.getBody().mensagem();
    }

    public String excluir(NotificacaoRecord notificacaoRecord) throws Exception {
        sec.remove("content-length");
        ResponseEntity<MensagemRetorno> responseEntity = restTemplate.postForEntity(
                CAMINHO + "/excluir",
                new HttpEntity<>(notificacaoRecord, sec),
                MensagemRetorno.class
        );
        return responseEntity.getBody().mensagem();
    }
}
