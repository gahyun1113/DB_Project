package com.db_project.db_project.messaging.kafka.consumer;

import com.db_project.db_project.entity.KafkaEntity;
import com.db_project.db_project.repository.KafkaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer{
    private final KafkaRepository kafkaRepository;

    @KafkaListener(topics = "test-topic", groupId = "kafka-group")
    public void consumeMessage(String message) {
        KafkaEntity kafka = new KafkaEntity();
        kafka.setContent(message);
        kafkaRepository.save(kafka);
        System.out.println("Consumed and saved message: " + message);
    }
}

