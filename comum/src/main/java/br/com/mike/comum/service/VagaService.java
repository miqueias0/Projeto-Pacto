package br.com.mike.comum.service;

import br.com.mike.comum.interceptor.Inteceptor;
import br.com.mike.comum.records.UsuarioRecord;
import br.com.mike.comum.records.VagaRecord;
import br.com.mike.comum.util.CarregarProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

public class VagaService {

    private RestTemplate restTemplate;

    private String CAMINHO;
    public VagaService(HttpHeaders sec) throws Exception{
        restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(new Inteceptor(sec)));
        CarregarProperties properties = new CarregarProperties("comum.properties");
        CAMINHO = properties.getProperties().getProperty("vaga");
    }

    public VagaRecord obterPorId(String id) throws Exception {
        return restTemplate.getForObject(CAMINHO + "/obterPorId?id={id}", VagaRecord .class, Map.of("id", id));
    }
}
