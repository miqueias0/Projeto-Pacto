package br.com.mike.candidatura.dao;

import br.com.mike.candidatura.modelo.Candidatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICandidatura extends JpaRepository<Candidatura, String> {

    Candidatura findByCandidatoIdAndVagaId(String candidatoId, String vagaId);

    List<Candidatura> findAllByCandidatoId(String candidatoId);

    List<Candidatura> findAllByVagaId(String vagaId);
}
