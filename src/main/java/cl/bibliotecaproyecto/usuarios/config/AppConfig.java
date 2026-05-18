package cl.bibliotecaproyecto.usuarios.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
    @Value("${ms.roles.url}")
    private String rolesUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(rolesUrl)
                .build();
    }
}
