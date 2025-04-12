package com.untilifoundyou.lostandfound.controller;

import com.untilifoundyou.lostandfound.enums.ItemStatus;
import com.untilifoundyou.lostandfound.repository.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/items/count")
public class ItemSummaryController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public ResponseEntity<ItemSummaryDTO> getItemSummary() {
        int lostCount = itemRepository.itemCountByStatus(ItemStatus.Lost);
        int foundCount = itemRepository.itemCountByStatus(ItemStatus.Found);
        int claimedCount = itemRepository.itemCountByStatus(ItemStatus.Claimed);

        ItemSummaryDTO summary = new ItemSummaryDTO(lostCount, foundCount, claimedCount);
        return ResponseEntity.ok(summary);
    }
}
