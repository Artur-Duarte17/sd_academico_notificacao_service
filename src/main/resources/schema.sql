CREATE TABLE notificacoes (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              aluno_id BIGINT,
                              turma_id BIGINT,
                              tipo VARCHAR(50),
                              canal VARCHAR(50),
                              mensagem VARCHAR(500),
                              status VARCHAR(50),
                              data_criacao TIMESTAMP,
                              data_envio TIMESTAMP
);