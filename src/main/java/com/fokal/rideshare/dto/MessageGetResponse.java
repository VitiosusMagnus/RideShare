package com.fokal.rideshare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageGetResponse {
    private Long id;
    private String content;
    private String timestamp;
    private UserGetResponse sender;
}
