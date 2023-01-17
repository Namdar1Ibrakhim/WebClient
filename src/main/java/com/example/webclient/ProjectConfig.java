package com.example.webclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ProjectConfig {

    @Bean
    public WebClient webClient(){
        return WebClient.builder().build(); //Создаем бин WebClient и добавляем его в контекст Spring
    }
    //WebClient работает на основе реактивного программирования, нету одной петли по которой работают все по очереди, он не соблюдая очередь может выполнить все
}
