package com.untilifoundyou.lostandfound.service;

import com.untilifoundyou.lostandfound.model.*;
import com.untilifoundyou.lostandfound.repository.ItemRepository;
import com.untilifoundyou.lostandfound.security.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public ItemService(ItemRepository itemRepository, JwtUtil jwtUtil) {
        this.itemRepository = itemRepository;
        this.jwtUtil = jwtUtil;
    }

    // CREATE ITEM
    public void create(Item item, MultipartFile file, String token) {
        Long authorId = extractUserIdFromToken(token);

        // Handle optional image upload
        if (file != null && !file.isEmpty()) {
            try {
                String uploadDir = "uploads/";
                File dir = new File(uploadDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                Path filePath = Paths.get(uploadDir, fileName);
                Files.copy(file.getInputStream(), filePath);

                item.setImagePath(uploadDir + fileName); // Save relative path
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload image", e);
            }
        }

        // Save item
        itemRepository.create(item, authorId);
    }

    // Extract userId from JWT token
    private Long extractUserIdFromToken(String token) {
        Long authorId = null;
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);  // Remove the "Bearer " prefix
            authorId = jwtUtil.extractUserId(jwtToken);  // Assuming "userId" is stored in the JWT token
        }

        // If no authorId is extracted, use a default (e.g., guest user)
        if (authorId == null) {
            authorId = 1L;  // Default guest user ID or fetch from DB if needed
        }

        return authorId;
    }

    // UPDATE ITEM
    public void update(Item item, Integer itemId) {
        itemRepository.update(item, itemId);
    }

    // DELETE ITEM
    public void delete(Integer itemId) {
        itemRepository.delete(itemId);
    }

    // RESTORE ITEM
    public void restore(Integer itemId) {
        itemRepository.restore(itemId);
    }

    // SAVE ALL ITEMS METHOD
    @Transactional
    public void saveAll(List<Item> items, Long authorId) {
        items.stream().forEach(item -> {
            item.setAuthorId(authorId); // Hardcode the authorId for each item
            itemRepository.create(item, 1L); // Now call the create method for each item
        });
    }
}
