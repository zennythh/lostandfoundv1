package com.untilifoundyou.lostandfound.service;

import com.untilifoundyou.lostandfound.model.Message;
import com.untilifoundyou.lostandfound.repository.MessageRepository;
import com.untilifoundyou.lostandfound.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public Message sendMessage(Long senderId, Long recipientId, String content) {
        Message message = new Message();
        message.setSender(userRepository.findById(senderId).orElseThrow());
        message.setRecipient(userRepository.findById(recipientId).orElseThrow());
        message.setContent(content);
        return messageRepository.save(message);
    }

    public List<Message> getChatHistory(Long user1Id, Long user2Id) {
        return messageRepository.findBySenderIdAndRecipientIdOrderByTimestampAsc(user1Id, user2Id);
    }
}

