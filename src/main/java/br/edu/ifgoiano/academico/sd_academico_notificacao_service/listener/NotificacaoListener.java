package br.edu.ifgoiano.academico.sd_academico_notificacao_service.listener;

import br.edu.ifgoiano.academico.sd_academico_notificacao_service.entity.Notificacao;
import br.edu.ifgoiano.academico.sd_academico_notificacao_service.repository.NotificacaoRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Listener para consumir mensagens da fila direta de notificações.
 * Recebe notificações do Matrícula Service e as armazena no banco de dados.
 */
@Service
public class NotificacaoListener {

    // Logger para rastreamento de operações
    private static final Logger logger = LoggerFactory.getLogger(NotificacaoListener.class);
    
    private final NotificacaoRepository notificacaoRepository;
    private final ObjectMapper objectMapper;

    /**
     * Construtor com injeção de dependências
     */
    public NotificacaoListener(NotificacaoRepository notificacaoRepository, ObjectMapper objectMapper) {
        this.notificacaoRepository = notificacaoRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Consome mensagens da fila fila.notificacoes
     * @param mensagem - JSON com os dados da notificação
     */
    @RabbitListener(queues = "fila.notificacoes")
    public void consumirNotificacao(String mensagem) {
        try {
            logger.info("[NOTIFICACAO-SERVICE] Notificação recebida da fila: {}", mensagem);

            // Desserializa a mensagem JSON
            JsonNode jsonNode = objectMapper.readTree(mensagem);

            // Extrai os dados da notificação
            Long alunoId = jsonNode.get("alunoId").asLong();
            Long turmaId = jsonNode.get("turmaId").asLong();
            String tipo = jsonNode.get("tipo").asText();
            String msgTexto = jsonNode.get("mensagem").asText();

            // Cria a entidade notificação
            Notificacao notificacao = new Notificacao(
                    alunoId,
                    turmaId,
                    tipo,
                    "EMAIL",
                    msgTexto,
                    "ENVIADA"
            );
            notificacao.setDataEnvio(LocalDateTime.now());

            // Persiste no banco de dados
            notificacaoRepository.save(notificacao);

            logger.info("[NOTIFICACAO-SERVICE] Notificação salva no H2 - ID: {}, Aluno: {}, Tipo: {}",
                    notificacao.getId(), alunoId, tipo);

        } catch (Exception e) {
            logger.error("[NOTIFICACAO-SERVICE] Erro ao processar notificação: {}", e.getMessage(), e);
        }
    }
}

