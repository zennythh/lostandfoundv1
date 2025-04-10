package com.untilifoundyou.lostandfound.json;

import com.untilifoundyou.lostandfound.repository.*;
import com.untilifoundyou.lostandfound.model.*;
import com.untilifoundyou.lostandfound.service.ItemService;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.aot.hint.TypeReference;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ItemsJson implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ItemsJson.class);
    private final ItemRepository itemRepository;
    private final ObjectMapper objectMapper;
    private final ItemService itemService;

    // Assuming you have a default authorId like 1 for the guest user
    private static final Long DEFAULT_AUTHOR_ID = 100L;

    public ItemsJson(ItemRepository itemRepository, ItemService itemService, ObjectMapper objectMapper){
        this.itemRepository = itemRepository;
        this.itemService = itemService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {

        // Check if there are no items in the DB, if so, load from JSON
        if (itemRepository.itemCount() == 0) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/items.json")) {
                Items allItems = objectMapper.readValue(inputStream, Items.class);
                log.info("Reading {} items from JSON data and saving to MySQL database.", allItems.items().size());

                // Here, pass the default authorId instead of token
                itemService.saveAll(allItems.items(), DEFAULT_AUTHOR_ID); // Pass the default authorId

            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
        } else {
            log.info("Not loading runs from JSON data as the repository has data.");
        }
    }
}
