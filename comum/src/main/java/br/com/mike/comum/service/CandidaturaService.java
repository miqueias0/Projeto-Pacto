package br.com.mike.comum.service;

import br.com.mike.comum.interceptor.Inteceptor;
import br.com.mike.comum.records.CandidaturaRecord;
import br.com.mike.comum.records.MensagemRetorno;
import br.com.mike.comum.util.CarregarProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CandidaturaService {

    private RestTemplate restTemplate;
    private String CAMINHO;
    public CandidaturaService(HttpHeaders sec) throws Exception{
        restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(new Inteceptor(sec)));
        CarregarProperties properties = new CarregarProperties("comum.properties");
        CAMINHO = properties.getProperties().getProperty("candidatura");
    }

    public CandidaturaRecord obterPorId() throws Exception {
        return restTemplate.getForObject(CAMINHO + "/obterPorId", CandidaturaRecord.class);
    }

    public List<CandidaturaRecord> obterListaPorCandidatoId(String id) throws Exception {
        ResponseEntity<List<CandidaturaRecord>> responseEntity = restTemplate.exchange(
                CAMINHO + "/obterListaPorCandidatoId?candidatoId=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CandidaturaRecord>>() {}
        );
        return responseEntity.getBody();
    }

    public List<CandidaturaRecord> obterListaPorVagaId(String id) throws Exception {
        ResponseEntity<List<CandidaturaRecord>> responseEntity = restTemplate.exchange(
                CAMINHO + "/obterListaPorVagaId?vagaId=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CandidaturaRecord>>() {}
        );
        return responseEntity.getBody();
    }

    public CandidaturaRecord manter(CandidaturaRecord candidaturaRecord) throws Exception {
        return restTemplate.postForObject(CAMINHO + "/manter", candidaturaRecord, CandidaturaRecord.class);
    }

    public MensagemRetorno excluir(CandidaturaRecord candidaturaRecord) throws Exception {
        restTemplate.postForEntity(CAMINHO + "/excluir", candidaturaRecord, Void.class);
        return new MensagemRetorno("Sucesso");
    }

}
