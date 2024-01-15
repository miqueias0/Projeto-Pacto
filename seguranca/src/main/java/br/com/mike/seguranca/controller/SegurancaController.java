package br.com.mike.seguranca.controller;

import br.com.mike.comum.records.AutenticacaoRecord;
import br.com.mike.comum.records.TokenRecord;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = "/seguranca")
public class SegurancaController {

    private final br.com.mike.seguranca.negocio.Seguranca seguranca = new br.com.mike.seguranca.negocio.Seguranca();

    @GetMapping(value = "/validarToken")
    @CrossOrigin("*")
    public ResponseEntity<AutenticacaoRecord> validarToken(@RequestHeader HttpHeaders sec) throws Exception{
        try{
            AutenticacaoRecord autenticacaoRecord = seguranca.converterAtuenticacao(seguranca.validarToken(Objects.requireNonNull(sec.get("authorization")).get(0)));
            return ResponseEntity.ok(autenticacaoRecord);
        } catch (Exception e) {
            throw new Exception("Erro ao validar Token pelo seguinte motivo: " + e.getLocalizedMessage());
        }
    }

    @GetMapping(value = "/criarToken")
    @CrossOrigin("*")
    public ResponseEntity<TokenRecord> criarToken(@RequestParam("id") String id, @RequestParam("tempoToken") Integer tempoToken) throws Exception{
        try{
            TokenRecord tokenRecord = new TokenRecord(seguranca.criarToken(id, tempoToken));
            return ResponseEntity.ok(tokenRecord);
        } catch (Exception e) {
            throw new Exception("Erro ao Criar Token pelo seguinte motivo: " + e.getLocalizedMessage());
        }
    }

    @GetMapping(value = "/atualizarToken")
    @CrossOrigin("*")
    public ResponseEntity<TokenRecord> atualizarToken(@RequestHeader HttpHeaders sec) throws Exception{
        try{
            TokenRecord tokenRecord = new TokenRecord(seguranca.criarToken(seguranca.validarToken(Objects.requireNonNull(sec.get("authorization")).get(0)).getIdentificador(), null));
            return ResponseEntity.ok(tokenRecord);
        } catch (Exception e) {
            throw new Exception("Erro ao atualizar Token pelo seguinte motivo: " + e.getLocalizedMessage());
        }
    }
}
