package br.edu.ifgoiano.academico.sd_academico_notificacao_service.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do RabbitMQ para o Notificação Service.
 * Define as filas, exchanges e bindings necessários para comunicação.
 */
@Configuration
public class RabbitMQConfig {

    // Nome da fila de notificações diretas (enviadas pelo Matrícula Service)
    public static final String FILA_NOTIFICACOES = "fila.notificacoes";

    // Nome do exchange para publicação de eventos (Pub/Sub)
    public static final String EXCHANGE_ACADEMICO = "academico.events";
    
    // Chaves de roteamento para eventos
    public static final String ROUTING_KEY_MATRICULA_CRIADA = "matricula.criada";
    public static final String ROUTING_KEY_MATRICULA_CANCELADA = "matricula.cancelada";

    // Fila para consumir eventos de matrícula
    public static final String QUEUE_EVENTO_NOTIFICACAO = "queue.evento.notificacao";

    /**
     * Define a fila direta de notificações
     */
    @Bean
    public Queue filaNotificacoes() {
        return new Queue(FILA_NOTIFICACOES, true);
    }

    /**
     * Define a fila para eventos de notificação (Pub/Sub)
     */
    @Bean
    public Queue queueEventoNotificacao() {
        return new Queue(QUEUE_EVENTO_NOTIFICACAO, true);
    }

    /**
     * Define o exchange de tipo Topic para publicação de eventos
     */
    @Bean
    public TopicExchange academicoExchange() {
        return new TopicExchange(EXCHANGE_ACADEMICO, true, false);
    }

    /**
     * Binding: conecta a fila ao exchange para eventos de matrícula criada
     */
    @Bean
    public Binding bindingMatriculaCriada(Queue queueEventoNotificacao, TopicExchange academicoExchange) {
        return BindingBuilder.bind(queueEventoNotificacao)
                .to(academicoExchange)
                .with(ROUTING_KEY_MATRICULA_CRIADA);
    }

    /**
     * Binding: conecta a fila ao exchange para eventos de matrícula cancelada
     */
    @Bean
    public Binding bindingMatriculaCancelada(Queue queueEventoNotificacao, TopicExchange academicoExchange) {
        return BindingBuilder.bind(queueEventoNotificacao)
                .to(academicoExchange)
                .with(ROUTING_KEY_MATRICULA_CANCELADA);
    }
}

