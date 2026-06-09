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
 * Listener para consumir eventos no padrão Pub/Sub do RabbitMQ.
 * Recebe eventos de negócio (matrícula criada/cancelada) e os processa como notificações.
 */
@Service
public class EventoNotificacaoListener {

    // Logger para rastreamento de operações
    private static final Logger logger = LoggerFactory.getLogger(EventoNotificacaoListener.class);
    
    private final NotificacaoRepository notificacaoRepository;
    private final ObjectMapper objectMapper;

    /**
     * Construtor com injeção de dependências
     */
    public EventoNotificacaoListener(NotificacaoRepository notificacaoRepository, ObjectMapper objectMapper) {
        this.notificacaoRepository = notificacaoRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Consome eventos da fila queue.evento.notificacao vinculada ao exchange academico.events
     * @param mensagem - JSON com os dados do evento
     */
    @RabbitListener(queues = "queue.evento.notificacao")
    public void consumirEvento(String mensagem) {
        try {
            logger.info("[NOTIFICACAO-SERVICE] Evento recebido: {}", mensagem);

            // Desserializa a mensagem JSON do evento
            JsonNode jsonNode = objectMapper.readTree(mensagem);

            // Extrai os dados do evento
            Long alunoId = jsonNode.get("alunoId").asLong();
            Long turmaId = jsonNode.get("turmaId").asLong();
            String tipo = jsonNode.get("tipo").asText();
            String descricao = jsonNode.get("descricao").asText();

            // Cria a entidade notificação a partir do evento
            Notificacao notificacao = new Notificacao(
                    alunoId,
                    turmaId,
                    tipo,
                    "SISTEMA",
                    descricao,
                    "PROCESSADA"
            );
            notificacao.setDataEnvio(LocalDateTime.now());

            // Persiste no banco de dados
            notificacaoRepository.save(notificacao);

            logger.info("[NOTIFICACAO-SERVICE] Evento processado e notificação registrada - Aluno: {}, Tipo: {}",
                    alunoId, tipo);

        } catch (Exception e) {
            logger.error("[NOTIFICACAO-SERVICE] Erro ao processar evento: {}", e.getMessage(), e);
        }
    }
}

