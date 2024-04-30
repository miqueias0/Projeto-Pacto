package br.com.mike.candidatura.controller;

import br.com.mike.candidatura.modelo.Candidatura;
import br.com.mike.candidatura.records.CandidaturaRecord;
import br.com.mike.candidatura.repository.CandidaturaService;
import br.com.mike.comum.records.AutenticacaoRecord;
import br.com.mike.comum.records.MensagemRetorno;
import br.com.mike.comum.records.NotificacaoRecord;
import br.com.mike.comum.service.AutenticationService;
import br.com.mike.comum.service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidatura")
public class CandidaturaController {

    @Autowired
    private CandidaturaService candidaturaService;

    @GetMapping(value = "/obterPorId")
    @CrossOrigin("*")
    public ResponseEntity<CandidaturaRecord> obterPorId(@RequestHeader HttpHeaders sec) throws Exception {
        try {
            AutenticacaoRecord autenticacaoRecord = new AutenticationService(sec).validarToken();
            CandidaturaRecord candidaturaRecord = converterCandidaturaRecord(candidaturaService.obterPorId(autenticacaoRecord.identificador()));
            return ResponseEntity.ok().body(candidaturaRecord);
        } catch (Exception e) {
            throw new Exception("Erro ao obter por id pelo seguinte motivo: " + (e.getLocalizedMessage() != null ? e.getLocalizedMessage(): ""));
        }
    }

    @GetMapping(value = "/obterListaPorCandidatoId")
    @CrossOrigin("*")
    public ResponseEntity<List<CandidaturaRecord>> obterListaPorCandidatoId(@RequestHeader HttpHeaders sec, @RequestParam("candidatoId") String candidatoId) throws Exception {
        try {
            AutenticacaoRecord autenticacaoRecord = new AutenticationService(sec).validarToken();
            List<CandidaturaRecord> candidaturaRecord = candidaturaService.obterListaPorCandidatoId(candidatoId).stream().map(this::converterCandidaturaRecord).toList();
            return ResponseEntity.ok().body(candidaturaRecord);
        } catch (Exception e) {
            throw new Exception("Erro ao obter lista pelo seguinte motivo: " + (e.getLocalizedMessage() != null ? e.getLocalizedMessage(): ""));
        }
    }

    @GetMapping(value = "/obterListaPorVagaId")
    @CrossOrigin("*")
    public ResponseEntity<List<CandidaturaRecord>> obterListaPorVagaId(@RequestHeader HttpHeaders sec, @RequestParam("vagaId") String vagaId) throws Exception {
        try {
            AutenticacaoRecord autenticacaoRecord = new AutenticationService(sec).validarToken();
            List<CandidaturaRecord> candidaturaRecord = candidaturaService.obterListaPorVagaId(vagaId).stream().map(this::converterCandidaturaRecord).toList();
            return ResponseEntity.ok().body(candidaturaRecord);
        } catch (Exception e) {
            throw new Exception("Erro ao obter lista pelo seguinte motivo: " + (e.getLocalizedMessage() != null ? e.getLocalizedMessage(): ""));
        }
    }

    @PostMapping(value = "/manter")
    @CrossOrigin("*")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<CandidaturaRecord> manter(@RequestHeader HttpHeaders sec, @RequestBody CandidaturaRecord candidaturaRecord) throws Exception {
        try {
            AutenticacaoRecord autenticacaoRecord = new AutenticationService(sec).validarToken();
            Candidatura candidatura = converterCandidatura(candidaturaRecord);
            candidatura.setCandidatoId(autenticacaoRecord.identificador());
            CandidaturaRecord candidaturaRecordSalva = converterCandidaturaRecord(candidaturaService.manter(candidatura));
            String mensagem = new NotificacaoService(sec).manter(
                    new br.com.mike.comum.records.CandidaturaRecord(candidaturaRecordSalva.candidatoId(), candidaturaRecordSalva.vagaId()));
            return ResponseEntity.ok().body(candidaturaRecordSalva);
        } catch (Exception e) {
            throw new Exception("Erro ao manter pelo seguinte motivo: " + (e.getLocalizedMessage() != null ? e.getLocalizedMessage(): ""));
        }
    }

    @PostMapping(value = "/excluir")
    @CrossOrigin("*")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Void> excluir(@RequestHeader HttpHeaders sec, @RequestBody CandidaturaRecord candidaturaRecord) throws Exception {
        try {
            AutenticacaoRecord autenticacaoRecord = new AutenticationService(sec).validarToken();
            NotificacaoService notificacaoService = new NotificacaoService(sec);
            List<NotificacaoRecord> records = notificacaoService.obterListaPorVagaId(candidaturaRecord.vagaId());
            if(records != null){
                for(NotificacaoRecord record: records){
                    notificacaoService.excluir(record);
                }
            }
            candidaturaService.excluir(converterCandidatura(candidaturaRecord));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new Exception("Erro ao excluir pelo seguinte motivo: " + (e.getLocalizedMessage() != null ? e.getLocalizedMessage(): ""));
        }
    }


    private CandidaturaRecord converterCandidaturaRecord(Candidatura candidatura){
        return new CandidaturaRecord(candidatura.getCandidatoId(), candidatura.getVagaId());
    }

    private Candidatura converterCandidatura(CandidaturaRecord candidaturaRecord){
        Candidatura candidatura = new Candidatura();
        candidatura.setCandidatoId(candidaturaRecord.candidatoId());
        candidatura.setVagaId(candidaturaRecord.vagaId());
        return candidatura;
    }

}
