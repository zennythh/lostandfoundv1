package com.untilifoundyou.lostandfound.repository;

import com.untilifoundyou.lostandfound.model.Conversation;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ConversationRepository {

    private final JdbcClient jdbcClient;

    // Create a conversation
    public Conversation create(Long user1Id, Long user2Id) {
        long lowId = Math.min(user1Id, user2Id);
        long highId = Math.max(user1Id, user2Id);

        jdbcClient.sql("""
            INSERT INTO conversation (user1_id, user2_id, user_low_id, user_high_id)
            VALUES (?, ?, ?, ?)
        """)
                .params(user1Id, user2Id, lowId, highId)
                .update();

        Long generatedId = jdbcClient.sql("SELECT LAST_INSERT_ID()")
                .query(Long.class)
                .single();

        return jdbcClient.sql("SELECT * FROM conversation WHERE id = ?")
                .params(generatedId)
                .query(Conversation.class)
                .single();
    }

    // Get conversation by both user IDs
    public Optional<Conversation> findByUsers(Long user1Id, Long user2Id) {
        long lowId = Math.min(user1Id, user2Id);
        long highId = Math.max(user1Id, user2Id);

        return jdbcClient.sql("""
            SELECT * FROM conversation
            WHERE user_low_id = ? AND user_high_id = ?
        """)
                .params(lowId, highId)
                .query(Conversation.class)
                .optional();
    }

    // Get all conversations for a user
    public List<Conversation> findAllForUser(Long userId) {
        return jdbcClient.sql("""
            SELECT * FROM conversation
            WHERE user1_id = ? OR user2_id = ?
            ORDER BY created_at DESC
        """)
                .params(userId, userId)
                .query(Conversation.class)
                .list();
    }
}
