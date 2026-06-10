package br.edu.ifgoiano.academico.sd_academico_notificacao_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Dados de saída ao expor uma notificação pela API.
 */
@Getter
@Setter
@NoArgsConstructor
public class NotificacaoResponseDTO {
    private Long id;
    private Long alunoId;
    private Long turmaId;
    private String tipo;
    private String canal;
    private String mensagem;
    private String status;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEnvio;
}
