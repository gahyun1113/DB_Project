package com.db_project.db_project.messaging.rabbitmq.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageProducer2 {

    private final RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE_NAME = "message_exchange";

    @GetMapping("/send2")
    public String sendMessage(@RequestParam String message) {
        String routingKey;

        // 숫자 메시지인지 확인
        if (message.matches("\\d+")) { // 숫자로만 이루어진 경우
            routingKey = "number";
        } else { // 문자 메시지인 경우
            routingKey = "text";
        }

        // 메시지 전송
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, routingKey, message);
        System.out.println("Sent message: " + message + " to routing key: " + routingKey);
        return "Message sent: " + message;
    }
}

