package com.untilifoundyou.lostandfound.controller;

import com.untilifoundyou.lostandfound.repository.*;
import com.untilifoundyou.lostandfound.enums.*;
import com.untilifoundyou.lostandfound.model.*;
import com.untilifoundyou.lostandfound.service.ItemService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/items")
public class ItemController {

    public final ItemRepository itemRepository;
    private final ItemService itemService;

    public ItemController(ItemRepository itemRepository, ItemService itemService){
        this.itemRepository = itemRepository;
        this.itemService = itemService;
    }
    
    @GetMapping("")
    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    public Item findByID(@PathVariable("id") Long id){

        Optional<Item> item = itemRepository.findById(id);
        if(item.isEmpty()){
            throw new ItemNotFoundException();
        }
        return item.get();
    }

    // CREATE ITEM
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/report", consumes = "multipart/form-data")
    public ResponseEntity<?> create(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("location") String location,
            @RequestParam(value = "foundOn", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime foundOn,
            @RequestParam("status") String status,
            @RequestParam("campus") String campus,
            @RequestParam("category") String category,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestHeader("Authorization") String token
    ) {
        Item item = new Item();
        item.setName(name);
        item.setDescription(description);
        item.setLocation(location);
        item.setFoundOn(foundOn);
        item.setStatus(ItemStatus.valueOf(status));
        item.setCampus(ItemCampus.valueOf(campus));
        item.setCategory(ItemCategory.valueOf(category));

        itemService.create(item, file, token);

        return ResponseEntity.ok(Map.of("message", "Item submitted successfully"));
    }

    //UPDATE ITEM
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/update/{id}")
    public void update(@Valid @RequestBody Item item, @PathVariable("id") Integer id){
        itemRepository.update(item, id);
    }

    //RESTORE ITEM
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/restore/{id}")
    public void restore(@PathVariable("id") Integer id){
        itemRepository.restore(id);
    }

    //DELETE ITEM
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        itemRepository.delete(id);
    }
}
