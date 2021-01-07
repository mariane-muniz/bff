package br.com.sascar.positionsconfigurationsbff.configs;

import java.util.Objects;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestTemplateConfig {
    private final RestTemplateBuilder restTemplateBuilder;
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        if (Objects.isNull(this.restTemplate)) {
            this.restTemplate = this.restTemplateBuilder
                // .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
        }
        return restTemplate;
    }
}