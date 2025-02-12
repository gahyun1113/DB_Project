package com.db_project.db_project.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Exchange 이름
    private static final String EXCHANGE_NAME = "message_exchange";

    // Queue 이름
    private static final String NUMBER_QUEUE = "number_queue";
    private static final String TEXT_QUEUE = "text_queue";
    private static final String TEST_QUEUE = "test_queue";

    // Exchange 생성
    @Bean
    public DirectExchange messageExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    // 숫자 큐 생성
    @Bean
    public Queue numberQueue() {
        return new Queue(NUMBER_QUEUE);
    }

    // 문자 큐 생성
    @Bean
    public Queue textQueue() {
        return new Queue(TEXT_QUEUE);
    }

    @Bean
    public Queue testQueue() {
        return new Queue(TEST_QUEUE);
    }


    // 숫자 큐와 바인딩
    @Bean
    public Binding bindNumberQueue(Queue numberQueue, DirectExchange messageExchange) {
        return BindingBuilder.bind(numberQueue).to(messageExchange).with("number");
    }

    // 문자 큐와 바인딩
    @Bean
    public Binding bindTextQueue(Queue textQueue, DirectExchange messageExchange) {
        return BindingBuilder.bind(textQueue).to(messageExchange).with("text");
    }
}
