package com.untilifoundyou.lostandfound.controller;

import com.untilifoundyou.lostandfound.model.Message;
import com.untilifoundyou.lostandfound.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        Message sent = messageService.sendMessage(message.getSender().getId(), message.getRecipient().getId(), message.getContent());
        return ResponseEntity.ok(sent);
    }

    @GetMapping("/history")
    public ResponseEntity<List<Message>> getChatHistory(
            @RequestParam Long user1Id,
            @RequestParam Long user2Id) {
        return ResponseEntity.ok(messageService.getChatHistory(user1Id, user2Id));
    }
}
