package io.github.vahansahakyan.CodeInspect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DbChangeProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendDbChangeMessage(String message) {
        kafkaTemplate.send("db-changes", message);
    }
}