package br.edu.ifgoiano.academico.sd_academico_notificacao_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidade que representa uma notificação enviada no sistema acadêmico.
 * Armazena informações sobre notificações de matrículas e eventos.
 */
@Entity
@Table(name = "notificacoes")
public class Notificacao {

    // Identificador único da notificação
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Identificador do aluno que recebe a notificação
    @Column(nullable = false)
    private Long alunoId;

    // Identificador da turma relacionada à notificação
    @Column(nullable = false)
    private Long turmaId;

    // Tipo de notificação (ex: MATRICULA_CRIADA, MATRICULA_CANCELADA)
    @Column(nullable = false)
    private String tipo;

    // Canal de envio da notificação (EMAIL, SMS, SISTEMA)
    @Column(nullable = false)
    private String canal;

    // Texto da mensagem da notificação
    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensagem;

    // Status da notificação (ENVIADA, PROCESSADA, PENDENTE)
    @Column(nullable = false)
    private String status;

    // Data e hora de criação da notificação
    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    // Data e hora de envio da notificação
    @Column
    private LocalDateTime dataEnvio;

    /**
     * Construtor padrão (necessário para JPA)
     */
    public Notificacao() {
    }

    /**
     * Construtor com parâmetros principais
     */
    public Notificacao(Long alunoId, Long turmaId, String tipo, String canal, String mensagem, String status) {
        this.alunoId = alunoId;
        this.dataCriacao = LocalDateTime.now();
        this.tipo = tipo;
        this.mensagem = mensagem;
        this.turmaId = turmaId;
        this.canal = canal;
        this.status = status;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Long turmaId) {
        this.turmaId = turmaId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    @Override
    public String toString() {
        return "Notificacao{" +
                "id=" + id +
                ", alunoId=" + alunoId +
                ", turmaId=" + turmaId +
                ", tipo='" + tipo + '\'' +
                ", canal='" + canal + '\'' +
                ", status='" + status + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", dataEnvio=" + dataEnvio +
                '}';
    }
}


