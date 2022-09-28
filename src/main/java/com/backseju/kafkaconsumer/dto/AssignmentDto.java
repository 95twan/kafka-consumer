package com.backseju.kafkaconsumer.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AssignmentDto {
    private Long id;

    private String uploadUrl;
}
