package br.edu.ifgoiano.academico.sd_academico_notificacao_service.repository;

import br.edu.ifgoiano.academico.sd_academico_notificacao_service.entity.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório para acesso aos dados de notificações.
 * Fornece operações CRUD e consultas personalizadas.
 */
@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
    /**
     * Busca todas as notificações de um aluno específico
     * @param alunoId identificador do aluno
     * @return lista de notificações do aluno
     */
    List<Notificacao> findByAlunoId(Long alunoId);
    
    /**
     * Busca todas as notificações com um status específico
     * @param status status das notificações
     * @return lista de notificações com o status especificado
     */
    List<Notificacao> findByStatus(String status);
}

