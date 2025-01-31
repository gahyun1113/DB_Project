package com.db_project.db_project.messaging.kafka.producer;



import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    // application.yml에서 설정한 토픽 값을 가져옴
    @Value("${spring.kafka.topic}")
    private String topic;

    public void sendMessage(String message) {
        kafkaTemplate.send(topic, message);
    }
}
