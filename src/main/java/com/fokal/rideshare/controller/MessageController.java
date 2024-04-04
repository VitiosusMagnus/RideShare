package com.fokal.rideshare.controller;

import com.fokal.rideshare.dto.MessageCreateRequest;
import com.fokal.rideshare.dto.MessageGetResponse;
import com.fokal.rideshare.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/messages")
public class MessageController {

        private final MessageService messageService;

        @GetMapping("/{user1Id}/{user2Id}/{page}")
        public List<MessageGetResponse> getMessages(@PathVariable Long user1Id, @PathVariable Long user2Id, @PathVariable Integer page){
            return messageService.getMessages(user1Id, user2Id, page);
        }
        @PostMapping("")
    public MessageGetResponse sendMessage(@RequestBody MessageCreateRequest messageCreateRequest) {
        return messageService.sendMessage(messageCreateRequest);
    }
}
