package com.untilifoundyou.lostandfound.service;

import com.untilifoundyou.lostandfound.model.Item;
import com.untilifoundyou.lostandfound.repository.ItemRepository;
import com.untilifoundyou.lostandfound.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public ItemService(ItemRepository itemRepository, JwtUtil jwtUtil) {
        this.itemRepository = itemRepository;
        this.jwtUtil = jwtUtil;
    }

    // Create item logic
    public void createItem(Item item, String token) {
        Long authorId = extractUserIdFromJwt(token);

        if (authorId == null) {
            // If no user found in JWT, default to guest user
            authorId = getGuestUserId();
        }

        item.setAuthorId(authorId);  // Set author ID from JWT or guest

        itemRepository.create(item);  // Save item using the repository
    }

    // Helper method to extract user ID from JWT
    private Long extractUserIdFromJwt(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);  // Remove "Bearer " prefix
            return jwtUtil.extractUserId(jwtToken);  // Extract userId from JWT
        }
        return null;
    }

    // Helper method to get the guest user ID
    private Long getGuestUserId() {
        // Here you can implement a way to get the guest user ID from the database
        // For example, fetching the guest user from the database
        // This is just a placeholder
        return 1L;  // Return the guest user ID (you need to modify it to fit your actual data source)
    }
}
