package com.backseju.kafkaconsumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = "test", groupId = "test-kafka")
    public void consume(String message) {
        System.out.println("message: " + message);
    }
}
