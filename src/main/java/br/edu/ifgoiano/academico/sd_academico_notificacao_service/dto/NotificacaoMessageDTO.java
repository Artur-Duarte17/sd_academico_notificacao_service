package br.edu.ifgoiano.academico.sd_academico_notificacao_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO (Data Transfer Object) para representar mensagens de notificação
 * recebidas do RabbitMQ. Facilita a desserialização de JSON.
 */
public class NotificacaoMessageDTO {
    
    // Identificador do aluno
    @JsonProperty("alunoId")
    private Long alunoId;
    
    // Identificador da turma
    @JsonProperty("turmaId")
    private Long turmaId;
    
    // Tipo de notificação
    @JsonProperty("tipo")
    private String tipo;
    
    // Mensagem de notificação (usado em fila direta)
    @JsonProperty("mensagem")
    private String mensagem;
    
    // Descrição do evento (usado em Pub/Sub)
    @JsonProperty("descricao")
    private String descricao;

    /**
     * Construtor padrão (necessário para desserialização)
     */
    public NotificacaoMessageDTO() {
    }

    /**
     * Construtor com todos os parâmetros
     */
    public NotificacaoMessageDTO(Long alunoId, Long turmaId, String tipo, String mensagem, String descricao) {
        this.alunoId = alunoId;
        this.turmaId = turmaId;
        this.tipo = tipo;
        this.mensagem = mensagem;
        this.descricao = descricao;
    }

    // Getters e Setters
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

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

