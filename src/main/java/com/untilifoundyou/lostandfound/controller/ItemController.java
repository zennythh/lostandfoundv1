package com.untilifoundyou.lostandfound.controller;

import com.untilifoundyou.lostandfound.repository.*;
import com.untilifoundyou.lostandfound.model.*;
import com.untilifoundyou.lostandfound.service.ItemService;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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
    @PostMapping("")
    public void create(@Valid @RequestBody Item item, @RequestHeader("Authorization") String token) {
        itemService.create(item, token);
    }

    //UPDATE ITEM
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
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
