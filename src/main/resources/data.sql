-- Data.sql para testes (executado automaticamente na startup)
-- Este arquivo é opcional, serve para injetar dados de teste via H2

-- Notificações devem vir do RabbitMQ, então deixamos a tabela vazia
-- TESTES:
 INSERT INTO notificacoes (aluno_id, turma_id, tipo, canal, mensagem, status, data_criacao, data_envio)
VALUES (1, 1, 'MATRICULA_CRIADA', 'EMAIL', 'Matrícula realizada com sucesso', 'ENVIADA', NOW(), NOW());

