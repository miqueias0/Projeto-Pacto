package br.com.mike.notificacao.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Notificacao {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String id;
    @Column(name = "vaga_id", nullable = false, updatable = false)
    private String vagaId;
    @Column(name = "candidato_id", nullable = false, updatable = false)
    private String candidatoId;
    @Column(name = "contratante_id", nullable = false, updatable = false)
    private String contratanteId;
    @Column(nullable = false)
    private Boolean contratanteNotificado;
    @Column(nullable = false)
    private Boolean candidatoNotificado;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVagaId() {
        return vagaId;
    }

    public void setVagaId(String vagaId) {
        this.vagaId = vagaId;
    }

    public String getCandidatoId() {
        return candidatoId;
    }

    public void setCandidatoId(String candidatoId) {
        this.candidatoId = candidatoId;
    }

    public String getContratanteId() {
        return contratanteId;
    }

    public void setContratanteId(String contratanteId) {
        this.contratanteId = contratanteId;
    }

    public Boolean getContratanteNotificado() {
        return contratanteNotificado;
    }

    public void setContratanteNotificado(Boolean contratanteNotificado) {
        this.contratanteNotificado = contratanteNotificado;
    }

    public Boolean getCandidatoNotificado() {
        return candidatoNotificado;
    }

    public void setCandidatoNotificado(Boolean candidatoNotificado) {
        this.candidatoNotificado = candidatoNotificado;
    }
}
