package com.example.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class PaymentsProxy {

    private final WebClient webClient;

    @Value("${name.service.url}") //Извлекаем базовый URL из файла свойств
    private String url;

    public PaymentsProxy(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Payment> createPayment(String requestId, Payment payment) {
        return webClient.post()//Определяем HTTP-метод, который будет использоваться при вызове
                .uri(url + "/payment")//Определяем URI вызова
                .header("requestId", requestId)//Добавляем к запросу HTTP-заголовок.
                .body(Mono.just(payment), Payment.class)//Формируем тело HTTP-запроса
                .retrieve()//Отправляем HTTP-запрос получаем HTTP-ответ
                .bodyToMono(Payment.class);//Извлекаем тело HTTP-запроса
    }
    //Данный метод также не возвращает значение непосредственно. Вместо этого он
    //возвращает экземпляр Mono, что позволяет другим функциям подписываться на
    //него. Таким образом, процесс в приложении формируется не путем соединения
    //задач в поток, а через установление зависимостей между ними посредством поставщиков и потребителей
}
