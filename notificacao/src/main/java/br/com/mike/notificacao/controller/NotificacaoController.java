package br.com.mike.notificacao.controller;

import br.com.mike.comum.records.*;
import br.com.mike.comum.service.AutenticationService;
import br.com.mike.comum.service.UsuarioService;
import br.com.mike.comum.service.VagaService;
import br.com.mike.notificacao.modelo.Notificacao;
import br.com.mike.notificacao.records.NotificacaoRecord;
import br.com.mike.notificacao.repository.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/notificacao")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping(value = "/obterListaPorVagaId")
    @CrossOrigin("*")
    public ResponseEntity<List<NotificacaoRecord>> obterListaPorVagaId(@RequestHeader HttpHeaders sec, @RequestParam("vagaId") String vagaId) throws Exception {
        try {
            AutenticacaoRecord autenticacaoRecord = new AutenticationService(sec).validarToken();
            List<NotificacaoRecord> records = notificacaoService.obterListaPorVagaId(vagaId).stream().map(x -> new NotificacaoRecord(null, x.getId(), x.getVagaId())).toList();
            return ResponseEntity.ok().body(records);
        } catch (Exception e) {
            throw new Exception("Erro ao obter por id pelo seguinte motivo: " + (e.getLocalizedMessage() != null ? e.getLocalizedMessage(): ""));
        }
    }

    @GetMapping(value = "/obterListaPorContratante")
    @CrossOrigin("*")
    public ResponseEntity<List<NotificacaoRecord>> obterListaPorContratante(@RequestHeader HttpHeaders sec) throws Exception {
        try {
            AutenticacaoRecord autenticacaoRecord = new AutenticationService(sec).validarToken();
            List<NotificacaoRecord> records = new ArrayList<>();
            List<Notificacao> list = notificacaoService.obterListaPorContratante(autenticacaoRecord.identificador());
            if(list != null){
                for(Notificacao notificacao: list.stream().filter(x -> !x.getContratanteNotificado()).toList()){
                    VagaRecord vagaRecord = new VagaService(sec).obterPorId(notificacao.getVagaId());
                    UsuarioRecord usuarioRecord = new UsuarioService(sec).obterPorId(notificacao.getCandidatoId());
                    String mensagem = "Candidato '" + usuarioRecord.nomeUsuario() + "'";
                    mensagem += " candidatou-se para a vaga de '" + vagaRecord.titulo() + "'";
                    records.add(montarNotificacaoRecord(mensagem, notificacao.getId(), notificacao.getVagaId()));
                }
            }
            return ResponseEntity.ok().body(records);
        } catch (Exception e) {
            throw new Exception("Erro ao obter por id pelo seguinte motivo: " + (e.getLocalizedMessage() != null ? e.getLocalizedMessage(): ""));
        }
    }

    @GetMapping(value = "/obterListaPorCandidato")
    @CrossOrigin("*")
    public ResponseEntity<List<NotificacaoRecord>> obterListaPorCandidato(@RequestHeader HttpHeaders sec) throws Exception {
        try {
            AutenticacaoRecord autenticacaoRecord = new AutenticationService(sec).validarToken();
            List<NotificacaoRecord> records = new ArrayList<>();
            List<Notificacao> list = notificacaoService.obterListaPorCandidato(autenticacaoRecord.identificador());
            if(list != null){
                for(Notificacao notificacao: list.stream().filter(x -> !x.getCandidatoNotificado() && x.getContratanteNotificado()).toList()){
                    VagaRecord vagaRecord = new VagaService(sec).obterPorId(notificacao.getVagaId());
                    UsuarioRecord usuarioRecord = new UsuarioService(sec).obterPorId(vagaRecord.contratanteId());
                    String mensagem = "Contratante '" + usuarioRecord.nomeUsuario() + "'";
                    mensagem += " visualizou sua candidatura para a vaga de '" + vagaRecord.titulo() + "'";
                    records.add(montarNotificacaoRecord(mensagem, notificacao.getId(), notificacao.getVagaId()));
                }
            }
            return ResponseEntity.ok().body(records);
        } catch (Exception e) {
            throw new Exception("Erro ao obter por id pelo seguinte motivo: " + (e.getLocalizedMessage() != null ? e.getLocalizedMessage(): ""));
        }
    }

    @PostMapping(value = "/manter", consumes = "application/json", produces = "application/json")
    @CrossOrigin("*")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<MensagemRetorno> manter(@RequestHeader HttpHeaders sec, @RequestBody CandidaturaRecord candidaturaRecord) throws Exception {
        try {
            AutenticacaoRecord autenticacaoRecord = new AutenticationService(sec).validarToken();
            VagaRecord vagaRecord = new VagaService(sec).obterPorId(candidaturaRecord.vagaId());
            Notificacao notificacao = new Notificacao();
            notificacao.setCandidatoId(autenticacaoRecord.identificador());
            notificacao.setVagaId(candidaturaRecord.vagaId());
            notificacao.setContratanteId(vagaRecord.contratanteId());
            notificacao.setCandidatoNotificado(Boolean.FALSE);
            notificacao.setContratanteNotificado(Boolean.FALSE);
            notificacaoService.manter(notificacao);
            return ResponseEntity.ok().body(new MensagemRetorno("Sucesso"));
        } catch (Exception e) {
            throw new Exception("Erro ao manter notificacao pelo seguinte motivo: " + (e.getLocalizedMessage() != null ? e.getLocalizedMessage(): ""));
        }
    }

    @PostMapping(value = "/alterar")
    @CrossOrigin("*")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<MensagemRetorno> alterar(@RequestHeader HttpHeaders sec, @RequestBody NotificacaoRecord notificacaoRecord) throws Exception {
        try {
            AutenticacaoRecord autenticacaoRecord = new AutenticationService(sec).validarToken();
            Notificacao notificacao = notificacaoService.obterPorId(notificacaoRecord.id());
            UsuarioRecord usuarioRecord = new UsuarioService(sec).obterPorId();
            if("Contratante".equalsIgnoreCase(usuarioRecord.tipoUsuario())){
                notificacao.setContratanteNotificado(Boolean.TRUE);
            }else {
                notificacao.setCandidatoNotificado(Boolean.TRUE);
            }
            notificacaoService.alterar(notificacao);
            return ResponseEntity.ok().body(new MensagemRetorno("Sucesso"));
        } catch (Exception e) {
            throw new Exception("Erro ao alterar notificacao pelo seguinte motivo: " + (e.getLocalizedMessage() != null ? e.getLocalizedMessage(): ""));
        }
    }

    @PostMapping(value = "/excluir")
    @CrossOrigin("*")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<MensagemRetorno> excluir(@RequestHeader HttpHeaders sec, @RequestBody NotificacaoRecord notificacaoRecord) throws Exception {
        try {
            AutenticacaoRecord autenticacaoRecord = new AutenticationService(sec).validarToken();
            notificacaoService.excluir(notificacaoService.obterPorId(notificacaoRecord.id()));
            return ResponseEntity.ok().body(new MensagemRetorno("Sucesso"));
        } catch (Exception e) {
            throw new Exception("Erro ao alterar notificacao pelo seguinte motivo: " + (e.getLocalizedMessage() != null ? e.getLocalizedMessage(): ""));
        }
    }





    private NotificacaoRecord montarNotificacaoRecord(String mensagem, String id, String vagaId){
        return new NotificacaoRecord(mensagem, id, vagaId);
    }
}
