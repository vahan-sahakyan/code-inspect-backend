package io.github.vahansahakyan.CodeInspect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbChangeService {
    @Autowired
    private DbChangeProducer dbChangeProducer;

    public void processDbChange(String changeDetails) {
        // Process the database change
        System.out.println(changeDetails);
        // Publish a message to Kafka
        dbChangeProducer.sendDbChangeMessage(changeDetails);
    }
}