package br.edu.ifgoiano.academico.sd_academico_notificacao_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Aplicação Spring Boot para o Serviço de Notificação.
 * Implementa o cliente de descoberta de serviços (Eureka) e processa
 * notificações através de mensagens RabbitMQ.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SdAcademicoNotificacaoServiceApplication {

	/**
	 * Ponto de entrada da aplicação
	 */
	public static void main(String[] args) {
		SpringApplication.run(SdAcademicoNotificacaoServiceApplication.class, args);
	}

}
