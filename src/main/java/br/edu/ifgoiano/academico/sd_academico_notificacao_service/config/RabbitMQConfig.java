package br.edu.ifgoiano.academico.sd_academico_notificacao_service.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Declarables;

@Configuration
public class RabbitMQConfig {

    // ======================
    // FILA DIRETA (RPC-style)
    // ======================
    public static final String FILA_NOTIFICACOES = "fila.notificacoes";

    // ======================
    // PUB/SUB (EVENTOS)
    // ======================
    public static final String EXCHANGE_ACADEMICO = "academico.events";

    public static final String ROUTING_KEY_MATRICULA_CRIADA = "matricula.criada";
    public static final String ROUTING_KEY_MATRICULA_CANCELADA = "matricula.cancelada";

    public static final String QUEUE_EVENTO_NOTIFICACAO = "queue.evento.notificacao";

    // ======================
    // FILA DIRETA
    // ======================
    @Bean
    public Queue filaNotificacoes() {
        return new Queue(FILA_NOTIFICACOES, true);
    }

    // ======================
    // FILA EVENTOS
    // ======================
    @Bean
    public Queue queueEventoNotificacao() {
        return new Queue(QUEUE_EVENTO_NOTIFICACAO, true);
    }

    // ======================
    // EXCHANGE
    // ======================
    @Bean
    public TopicExchange academicoExchange() {
        return new TopicExchange(EXCHANGE_ACADEMICO, true, false);
    }

    // ======================
    // BINDINGS
    // ======================
    @Bean
    public Binding bindingMatriculaCriada(
            Queue queueEventoNotificacao,
            TopicExchange academicoExchange) {

        return BindingBuilder
                .bind(queueEventoNotificacao)
                .to(academicoExchange)
                .with(ROUTING_KEY_MATRICULA_CRIADA);
    }

    @Bean
    public Binding bindingMatriculaCancelada(
            Queue queueEventoNotificacao,
            TopicExchange academicoExchange) {

        return BindingBuilder
                .bind(queueEventoNotificacao)
                .to(academicoExchange)
                .with(ROUTING_KEY_MATRICULA_CANCELADA);
    }

    // ======================
    // JSON CONVERTER (ESSENCIAL)
    // ======================
    @Bean
    public MessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setAlwaysConvertToInferredType(true);
        return converter;
    }

    @Bean
    public Declarables loggingDeclarables() {
        TopicExchange logsExchange = new TopicExchange("logs.academico", true, false);
        Queue logsQueue = new Queue("logs.all", true);
        Binding logsBinding = BindingBuilder.bind(logsQueue).to(logsExchange).with("logs.#");
        return new Declarables(logsExchange, logsQueue, logsBinding);
    }
}