package com.db_project.db_project.messaging.kafka.controller;

import com.db_project.db_project.messaging.kafka.consumer.KafkaConsumer;
import com.db_project.db_project.messaging.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
@RequiredArgsConstructor
public class KafkaController {
    private final KafkaProducer kafkaProducer;

    @PostMapping("/publish")
    public String publish(@RequestParam String message) {
        kafkaProducer.sendMessage(message);
        return "Message sent to Kafka: " + message;
    }
}

