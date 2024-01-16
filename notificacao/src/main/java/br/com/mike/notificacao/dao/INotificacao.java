package br.com.mike.notificacao.dao;

import br.com.mike.notificacao.modelo.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface INotificacao extends JpaRepository<Notificacao, String> {

    List<Notificacao> findAllByCandidatoId(String candidatoId);

    List<Notificacao> findAllByContratanteId(String contratanteId);

    List<Notificacao> findAllByVagaId(String vagaId);

}
