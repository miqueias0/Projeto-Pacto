 package br.com.mike.comum.service;

import br.com.mike.comum.interceptor.Inteceptor;
import br.com.mike.comum.records.AutenticacaoRecord;
import br.com.mike.comum.records.TokenRecord;
import br.com.mike.comum.util.CarregarProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

 @Component
 public class AutenticationService {

     private RestTemplate restTemplate;

     private String CAMINHO;
     public AutenticationService(HttpHeaders sec) throws Exception{
         restTemplate = new RestTemplate();
         restTemplate.setInterceptors(Collections.singletonList(new Inteceptor(sec)));
         CarregarProperties properties = new CarregarProperties("comum.properties");
         CAMINHO = properties.getProperties().getProperty("seguranca");
     }

     public AutenticacaoRecord validarToken() throws Exception {
         return restTemplate.getForObject(CAMINHO + "/validarToken", AutenticacaoRecord.class);
     }

     public TokenRecord criarToken(String id, Integer tempoToken) throws Exception {
         return restTemplate.getForObject(CAMINHO + "/criarToken?id={id}&tempoToken={tempoToken}", TokenRecord.class, Map.of("id", id, "tempoToken", tempoToken));
     }

     public TokenRecord atualizarToken() throws Exception {
         return restTemplate.getForObject(CAMINHO + "/atualizarToken", TokenRecord.class);
     }
 }
