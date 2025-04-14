package com.untilifoundyou.lostandfound.repository;

import com.untilifoundyou.lostandfound.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderIdAndRecipientIdOrderByTimestampAsc(Long senderId, Long recipientId);
    List<Message> findByRecipientIdAndSeenFalse(Long recipientId);
}
