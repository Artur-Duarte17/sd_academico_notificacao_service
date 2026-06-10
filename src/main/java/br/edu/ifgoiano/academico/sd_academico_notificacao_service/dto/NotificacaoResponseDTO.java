package br.edu.ifgoiano.academico.sd_academico_notificacao_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Representação de uma notificação retornada pela API")
public class NotificacaoResponseDTO {

    @Schema(description = "Identificador da notificação", example = "1")
    private Long id;

    @Schema(description = "ID do aluno", example = "1")
    private Long alunoId;

    @Schema(description = "ID da turma", example = "1")
    private Long turmaId;

    @Schema(description = "Tipo do evento", example = "MATRICULA_CRIADA")
    private String tipo;

    @Schema(description = "Canal de envio", example = "SISTEMA")
    private String canal;

    @Schema(description = "Texto da notificação", example = "Matrícula criada para o aluno 1 na turma 1.")
    private String mensagem;

    @Schema(description = "Status da notificação", example = "PROCESSADA")
    private String status;

    @Schema(description = "Data/hora de criação", example = "2026-06-10T14:30:00")
    private LocalDateTime dataCriacao;

    @Schema(description = "Data/hora de envio", example = "2026-06-10T14:30:01")
    private LocalDateTime dataEnvio;
}
