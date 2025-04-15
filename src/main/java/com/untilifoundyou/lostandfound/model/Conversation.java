package com.untilifoundyou.lostandfound.model;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Table(name = "conversation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conversation {
    private Long id;
    private Long user1Id;
    private Long user2Id;
    private Long userLowId;
    private Long userHighId;
    private LocalDateTime createdAt;

    public Conversation(Long user1Id, Long user2Id) {
        this.user1Id = user1Id;
        this.user2Id = user2Id;
        this.userLowId = Math.min(user1Id, user2Id);
        this.userHighId = Math.max(user1Id, user2Id);
        this.createdAt = LocalDateTime.now();
    }
}