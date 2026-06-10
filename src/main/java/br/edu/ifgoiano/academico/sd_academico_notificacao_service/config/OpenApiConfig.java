package br.edu.ifgoiano.academico.sd_academico_notificacao_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do OpenAPI/Swagger.
 *
 * Após subir o serviço, a documentação interativa fica disponível em:
 *   - Swagger UI:   http://localhost:8085/swagger-ui.html
 *   - OpenAPI JSON: http://localhost:8085/v3/api-docs
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI notificacaoServiceOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Notificação Service API")
                .description("API de consulta das notificações geradas pelos eventos de matrícula.")
                .version("v1"));
    }
}
