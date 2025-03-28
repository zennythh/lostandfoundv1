package com.untilifoundyou.lostandfound.json;

import com.untilifoundyou.lostandfound.repository.*;
import com.untilifoundyou.lostandfound.model.*;
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

    public ItemsJson(ItemRepository itemRepository, ObjectMapper objectMapper){
        this.itemRepository = itemRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {

        if (itemRepository.count()==0) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/items.json")) {
                Items allItems = objectMapper.readValue(inputStream, Items.class);
                log.info("Reading {} items from JSON data and saving to MySQL database.", allItems.items().size());
                itemRepository.saveAll(allItems.items());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
        } else {
            log.info("Not loading runs from JSON data as the repository has data.");
        }

    }

}