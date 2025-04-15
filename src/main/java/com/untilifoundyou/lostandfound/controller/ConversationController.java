package com.untilifoundyou.lostandfound.controller;

import com.untilifoundyou.lostandfound.model.Conversation;
import com.untilifoundyou.lostandfound.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conversations")
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;

    // Start or get an existing conversation between two users
    @PostMapping("/start")
    @ResponseStatus(HttpStatus.CREATED)
    public Conversation startConversation(@RequestHeader("Authorization") String token,
                                          @RequestParam("user2Id") Long user2Id) {
        return conversationService.createConversation(token, user2Id);
    }

    @GetMapping("/user")
    public List<Conversation> getUserConversations(@RequestHeader("Authorization") String token) {
        return conversationService.getUserConversations(token);
    }
}
