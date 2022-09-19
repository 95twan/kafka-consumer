package com.backseju.kafkaconsumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = "test", groupId = "test-kafka")
    public void consume(Long message) {
        System.out.println("message: " + message);
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("sh", "-c", "docker images");

        try {
            Process process = builder.start();

            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append('\n');
            }

            System.out.println(output);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
