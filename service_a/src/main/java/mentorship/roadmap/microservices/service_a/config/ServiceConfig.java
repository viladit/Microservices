package mentorship.roadmap.microservices.service_a.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ServiceConfig {
    @Bean
    RestTemplate restTemplate(RestTemplateBuilder b) {
        return b.build();
    }
}
