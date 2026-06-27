package com.pushkar.taskorchestrator.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI taskOrchestratorOpenAPI() {

        return new OpenAPI()

                .info(new Info()

                        .title("Virtual Threads Task Orchestrator API")

                        .description("Spring Boot + Java 21 Virtual Threads Task Orchestrator")

                        .version("1.0")

                        .contact(new Contact()
                                .name("Pushkar Prajapati")
                                .email("kppusakar2018gmail.com")))

                .externalDocs(new ExternalDocumentation()

                        .description("GitHub Repository")

                        .url("https://github.com/your-github-link"));
    }
}