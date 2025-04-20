package io.github.vahansahakyan.CodeInspect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DbChangeProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final Boolean kafkaEnabled = Boolean.parseBoolean(System.getenv("ENABLE_KAFKA"));

    public void sendDbChangeMessage(String message) {
        if (this.kafkaEnabled) {
            kafkaTemplate.send("db-changes", message);
        }
    }
}