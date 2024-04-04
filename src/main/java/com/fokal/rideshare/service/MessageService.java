package com.fokal.rideshare.service;


import com.fokal.rideshare.dto.MessageCreateRequest;
import com.fokal.rideshare.dto.MessageGetResponse;
import com.fokal.rideshare.model.Message;
import com.fokal.rideshare.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;
    public List<MessageGetResponse> getMessages(Long user1Id, Long user2Id, int page) {
        return messageRepository.getChat(user1Id, user2Id).stream()
                .skip(page * 10)
                .limit(10)
                .map(message -> modelMapper.map(message, MessageGetResponse.class))
                .toList();
    }

    public MessageGetResponse sendMessage(MessageCreateRequest messageCreateRequest) {
        return modelMapper.map(messageRepository.save(modelMapper.map(messageCreateRequest, Message.class)), MessageGetResponse.class);
    }
}
