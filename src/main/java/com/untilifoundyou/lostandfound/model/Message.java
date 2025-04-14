package com.untilifoundyou.lostandfound.model;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "message")
@Getter
@Setter
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User recipient;

    private String content;

    private LocalDateTime timestamp = LocalDateTime.now();

    private boolean seen = false;
}
