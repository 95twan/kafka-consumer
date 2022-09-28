package com.backseju.kafkaconsumer.service;

import com.backseju.kafkaconsumer.dto.AssignmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final JudgeService judgeService;

    @KafkaListener(topics = "test", groupId = "test-kafka")
    public void consume(AssignmentDto assignment) {
        String fileUrl = judgeService.downloadAssignment(assignment.getUploadUrl());
        judgeService.unzipAssignment(fileUrl);
    }
}
