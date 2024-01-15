package br.com.mike.candidatura.repository;

import br.com.mike.candidatura.dao.ICandidatura;
import br.com.mike.candidatura.modelo.Candidatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CandidaturaService {

    @Autowired
    private ICandidatura iCandidatura;

    public Candidatura obterPorId(String id){
        return iCandidatura.findById(id).orElse(null);
    }

    public List<Candidatura> obterListaPorVagaId(String id){
        return iCandidatura.findAllByVagaId(id);
    }

    public List<Candidatura> obterListaPorCandidatoId(String id){
        return iCandidatura.findAllByCandidatoId(id);
    }

    public Candidatura manter(Candidatura candidatura){
        candidatura.setId(UUID.randomUUID().toString());
        return iCandidatura.save(candidatura);
    }

    public void excluir(Candidatura candidatura){
        iCandidatura.delete(candidatura);
    }


}
