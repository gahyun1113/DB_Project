package com.db_project.db_project.messaging.rabbitmq.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("test-queue")
    private String queueName;

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        rabbitTemplate.convertAndSend(queueName, message);
        return "Message sent: " + message;
    }
}

