package com.db_project.db_project.messaging.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer2 {

    @RabbitListener(queues = "number_queue")
    public void handleNumberMessage(String message) {
        System.out.println("Received number message: " + message);
        // 숫자 메시지 처리 로직
    }

    @RabbitListener(queues = "text_queue")
    public void handleTextMessage(String message) {
        System.out.println("Received text message: " + message);
        // 문자 메시지 처리 로직
    }
}

