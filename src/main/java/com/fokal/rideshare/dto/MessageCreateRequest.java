package com.fokal.rideshare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageCreateRequest {
    private UserGetResponse sender;
    private UserGetResponse receiver;
    private String content;


}
