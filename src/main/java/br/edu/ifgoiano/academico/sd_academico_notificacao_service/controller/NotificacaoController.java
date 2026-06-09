package br.edu.ifgoiano.academico.sd_academico_notificacao_service.controller;

import br.edu.ifgoiano.academico.sd_academico_notificacao_service.entity.Notificacao;
import br.edu.ifgoiano.academico.sd_academico_notificacao_service.repository.NotificacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para gerenciar notificações.
 * Fornece endpoints para consultar notificações armazenadas no banco de dados.
 */
@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    // Logger para rastreamento de requisições
    private static final Logger logger = LoggerFactory.getLogger(NotificacaoController.class);
    
    private final NotificacaoRepository notificacaoRepository;

    /**
     * Construtor com injeção de dependências
     */
    public NotificacaoController(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }

    /**
     * Retorna todas as notificações armazenadas
     * @return lista de todas as notificações
     */
    @GetMapping
    public ResponseEntity<List<Notificacao>> listarTodasAsNotificacoes() {
        logger.info("[NOTIFICACAO-SERVICE] Listando todas as notificações");
        List<Notificacao> notificacoes = notificacaoRepository.findAll();
        return ResponseEntity.ok(notificacoes);
    }

    /**
     * Busca uma notificação específica por ID
     * @param id identificador da notificação
     * @return notificação encontrada ou 404 se não existir
     */
    @GetMapping("/{id}")
    public ResponseEntity<Notificacao> buscarNotificacao(@PathVariable Long id) {
        logger.info("[NOTIFICACAO-SERVICE] Buscando notificação ID: {}", id);
        return notificacaoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Retorna todas as notificações de um aluno específico
     * @param alunoId identificador do aluno
     * @return lista de notificações do aluno
     */
    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<Notificacao>> listarPorAluno(@PathVariable Long alunoId) {
        logger.info("[NOTIFICACAO-SERVICE] Listando notificações do aluno: {}", alunoId);
        List<Notificacao> notificacoes = notificacaoRepository.findByAlunoId(alunoId);
        return ResponseEntity.ok(notificacoes);
    }

    /**
     * Retorna todas as notificações com um status específico
     * @param status status das notificações (ENVIADA, PROCESSADA, PENDENTE)
     * @return lista de notificações com o status especificado
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Notificacao>> listarPorStatus(@PathVariable String status) {
        logger.info("[NOTIFICACAO-SERVICE] Listando notificações com status: {}", status);
        List<Notificacao> notificacoes = notificacaoRepository.findByStatus(status);
        return ResponseEntity.ok(notificacoes);
    }
}

