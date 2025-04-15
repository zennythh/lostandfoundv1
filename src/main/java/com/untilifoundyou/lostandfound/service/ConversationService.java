package com.untilifoundyou.lostandfound.service;

import com.untilifoundyou.lostandfound.model.Conversation;
import com.untilifoundyou.lostandfound.repository.ConversationRepository;
import com.untilifoundyou.lostandfound.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final JwtUtil jwtService;

    public Conversation createConversation(String token, Long user2Id) {
        Long user1Id = jwtService.extractUserId(token);

        // Try to find existing conversation first
        return conversationRepository.findByUsers(user1Id, user2Id)
                .orElseGet(() -> conversationRepository.create(user1Id, user2Id));
    }

    public List<Conversation> getUserConversations(String token) {
        Long userId = jwtService.extractUserId(token);
        return conversationRepository.findAllForUser(userId);
    }
}


