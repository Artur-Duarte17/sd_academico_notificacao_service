package br.edu.ifgoiano.academico.sd_academico_notificacao_service.Messaging.consumer;

import br.edu.ifgoiano.academico.sd_academico_notificacao_service.entity.Notificacao;
import br.edu.ifgoiano.academico.sd_academico_notificacao_service.Messaging.dto.MensagemNotificacao;
import br.edu.ifgoiano.academico.sd_academico_notificacao_service.repository.NotificacaoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationConsumer {

    private static final Logger logger = LoggerFactory.getLogger(NotificationConsumer.class);

    private final NotificacaoRepository repository;

    public NotificationConsumer(NotificacaoRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "fila.notificacoes")
    public void receberMensagem(MensagemNotificacao mensagem) {
        logger.info("[NOTIFICACAO-SERVICE] Mensagem recebida - Aluno: {}, Tipo: {}", mensagem.getAlunoId(), mensagem.getTipo());

        Notificacao notificacao = new Notificacao();
        notificacao.setAlunoId(mensagem.getAlunoId());
        notificacao.setTurmaId(mensagem.getTurmaId());
        notificacao.setTipo(mensagem.getTipo());
        notificacao.setMensagem(mensagem.getMensagem());
        notificacao.setCanal("SISTEMA");
        notificacao.setStatus("ENVIADA");
        notificacao.setDataCriacao(LocalDateTime.now());

        repository.save(notificacao);

        logger.info("[NOTIFICACAO-SERVICE] Notificação salva - Aluno: {}, Mensagem: {}", mensagem.getAlunoId(), mensagem.getMensagem());
    }
}