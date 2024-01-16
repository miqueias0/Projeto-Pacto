package br.com.mike.usuario.controller;

import br.com.mike.comum.records.AutenticacaoRecord;
import br.com.mike.comum.records.TokenRecord;
import br.com.mike.comum.service.AutenticationService;
import br.com.mike.usuario.modelo.Usuario;
import br.com.mike.usuario.record.LoginRecord;
import br.com.mike.usuario.record.UsuarioRecord;
import br.com.mike.usuario.repository.UsuarioService;
import io.quarkus.narayana.jta.QuarkusTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/obterPorId")
    @CrossOrigin("*")
    public ResponseEntity<UsuarioRecord> obterPorId(@RequestHeader HttpHeaders sec) throws Exception {
        try {
            AutenticacaoRecord autenticacaoRecord = new AutenticationService(sec).validarToken();
            UsuarioRecord usuarioRecord = converterUsuario(usuarioService.obterPorId(autenticacaoRecord.identificador()));
            return ResponseEntity.ok().body(usuarioRecord);
        } catch (Exception e) {
            throw new Exception("Erro ao obter por id pelo seguinte motivo: " + (e.getLocalizedMessage() != null ? e.getLocalizedMessage(): ""));
        }

    }

    @GetMapping(value = "/obterPorIdParam")
    @CrossOrigin("*")
    public ResponseEntity<UsuarioRecord> obterPorIdParam(@RequestHeader HttpHeaders sec, @RequestParam("id") String id) throws Exception {
        try {
            AutenticacaoRecord autenticacaoRecord = new AutenticationService(sec).validarToken();
            UsuarioRecord usuarioRecord = converterUsuario(usuarioService.obterPorId(id));
            return ResponseEntity.ok().body(usuarioRecord);
        } catch (Exception e) {
            throw new Exception("Erro ao obter por id pelo seguinte motivo: " + (e.getLocalizedMessage() != null ? e.getLocalizedMessage(): ""));
        }

    }

    @PostMapping(value = "/manter")
    @CrossOrigin("*")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<TokenRecord> manter(@RequestBody UsuarioRecord usuarioRecord, @RequestHeader HttpHeaders sec) throws Exception {
        try {
            Usuario usuario = usuarioService.manter(converterUsuarioRecord(usuarioRecord));
            TokenRecord token = new AutenticationService(sec)
                    .criarToken(usuario.getId(), 300);
            return new ResponseEntity<>(token, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception("Erro ao manter usuario pelo seguinte motivo: " + (e.getLocalizedMessage() != null ? e.getLocalizedMessage(): ""));
        }
    }

    @PostMapping(value = "/alterarUsuario")
    @CrossOrigin("*")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<UsuarioRecord> alterarUsuario(@RequestBody UsuarioRecord usuarioRecord, @RequestHeader HttpHeaders sec) throws Exception {
        try {
            new AutenticationService(sec).validarToken();
            QuarkusTransaction.begin();
            UsuarioRecord usuarioAtualizado = converterUsuario(usuarioService.update(converterUsuarioRecord(usuarioRecord)));
            QuarkusTransaction.commit();
            return ResponseEntity.ok().body(usuarioAtualizado);
        } catch (Exception e) {
            QuarkusTransaction.rollback();
            throw new RuntimeException("Erro ao alterar usuario pelo seguinte motivo: " + (e.getLocalizedMessage() != null ? e.getLocalizedMessage(): ""));
        }
    }

    @PostMapping(value = "/login")
    @CrossOrigin("*")
    public ResponseEntity<TokenRecord> login(@RequestBody LoginRecord loginRecord, @RequestHeader HttpHeaders sec) throws Exception{
        try {
            Usuario usuario = usuarioService.login(converterUsuarioRecord(loginRecord.usuario()));
            TokenRecord tokenRecord = new AutenticationService(sec)
                    .criarToken(usuario.getId(), loginRecord.manterLogado() == null || loginRecord.manterLogado() ? null: 300);
            return ResponseEntity.ok().body(tokenRecord);
        } catch (Exception e) {
            throw new Exception("Erro ao fazer login pelo seguinte motivo: " + (e.getLocalizedMessage() != null ? e.getLocalizedMessage(): ""));
        }
    }

    @GetMapping(value = "/loginComToken")
    @CrossOrigin("*")
    public ResponseEntity<TokenRecord> loginComToken(@RequestHeader HttpHeaders sec) {
        try {
            TokenRecord tokenRecord = new AutenticationService(sec).atualizarToken();
            return ResponseEntity.ok().body(tokenRecord);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Usuario converterUsuarioRecord(UsuarioRecord usuarioRecord){
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario(usuarioRecord.nomeUsuario());
        usuario.setTipoUsuario(usuarioRecord.tipoUsuario());
        usuario.setEmail(usuarioRecord.email());
        usuario.setTelefone(usuarioRecord.telefone());
        usuario.setSenha(usuarioRecord.senha());
        return usuario;
    }

    private UsuarioRecord converterUsuario(Usuario usuario){
        UsuarioRecord usuarioRecord = new UsuarioRecord(usuario.getNomeUsuario(), usuario.getEmail(), usuario.getTelefone(), null, usuario.getTipoUsuario());
        return usuarioRecord;
    }
}
