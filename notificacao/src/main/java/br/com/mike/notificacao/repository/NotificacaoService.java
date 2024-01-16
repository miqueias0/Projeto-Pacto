package br.com.mike.notificacao.repository;

import br.com.mike.notificacao.dao.INotificacao;
import br.com.mike.notificacao.modelo.Notificacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotificacaoService {
    @Autowired
    private INotificacao iNotificacao;

    public Notificacao obterPorId(String id){
        return iNotificacao.findById(id).orElse(null);
    }

    public List<Notificacao> obterListaPorVagaId(String id){
        return  iNotificacao.findAllByVagaId(id);
    }

    public List<Notificacao> obterListaPorContratante(String id){
        return  iNotificacao.findAllByContratanteId(id);
    }

    public List<Notificacao> obterListaPorCandidato(String id){
        return  iNotificacao.findAllByCandidatoId(id);
    }

    public Notificacao manter(Notificacao notificacao){
        notificacao.setId(UUID.randomUUID().toString());
        return iNotificacao.save(notificacao);
    }

    public Notificacao alterar(Notificacao notificacao){
        return iNotificacao.save(notificacao);
    }

    public void excluir(Notificacao notificacao){
        iNotificacao.delete(notificacao);
    }

}
