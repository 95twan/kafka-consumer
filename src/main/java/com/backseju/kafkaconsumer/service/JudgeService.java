package com.backseju.kafkaconsumer.service;

import com.backseju.kafkaconsumer.entity.Assignment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class JudgeService {

    public String downloadAssignment(String uploadUrl) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/assignments/file";
        URI uri = UriComponentsBuilder.fromHttpUrl(url).queryParam("assignmentUrl", uploadUrl).build().toUri();
        byte[] downloadFile = restTemplate.getForObject(uri, byte[].class);
        try{
            Path path = Files.write(Paths.get("/Users/twan/Desktop/project-oj/download/dummy.zip"), downloadFile);
            return path.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    public void unzipAssignment(String fileUrl) {
        ProcessBuilder builder = new ProcessBuilder();
        builder.directory(new File("/Users/twan/Desktop/project-oj"));
        // 실행 권한 필요
        builder.command("./kafkaConsumer/test.sh", fileUrl);

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
