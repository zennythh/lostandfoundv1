package com.untilifoundyou.lostandfound.service;

import com.untilifoundyou.lostandfound.model.Conversation;
import com.untilifoundyou.lostandfound.model.Message;
import com.untilifoundyou.lostandfound.service.ConversationService;
import com.untilifoundyou.lostandfound.repository.MessageRepository;
import com.untilifoundyou.lostandfound.repository.UserRepository;
import com.untilifoundyou.lostandfound.security.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final ConversationService conversationService;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository, JwtUtil jwtUtil, ConversationService conversationService) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.conversationService = conversationService;
    }

    public Message sendMessage(String token, Long recipientId, String content) {
        Long senderId = jwtUtil.extractUserId(token);

        Conversation conversation = conversationService.createConversation(token, recipientId);

        Message message = new Message();
        message.setSender(userRepository.findById(senderId).orElseThrow());
        message.setConversationId(conversation.getId());
        message.setRecipient(userRepository.findById(recipientId).orElseThrow());
        message.setContent(content);
        return messageRepository.save(message);
    }


    public List<Message> getChatHistory(Long user1Id, Long user2Id) {
        return messageRepository.findBySenderIdAndRecipientIdOrderByTimestampAsc(user1Id, user2Id);
    }
}

