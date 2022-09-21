package com.backseju.kafkaconsumer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final JudgeService judgeService;

    @KafkaListener(topics = "test", groupId = "test-kafka")
    public void consume(Long message) {
        String fileUrl = judgeService.downloadAssignment(message);
        judgeService.unzipAssignment(fileUrl);
    }
}
