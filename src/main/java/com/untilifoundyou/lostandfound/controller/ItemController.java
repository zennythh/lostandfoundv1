package com.untilifoundyou.lostandfound.controller;

import com.untilifoundyou.lostandfound.repository.*;
import com.untilifoundyou.lostandfound.model.*;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    public final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }
    
    @GetMapping("")
    public List<Item> findAll(){
        return itemRepository.findAll();
    }

/*    @GetMapping("/{id}")
    public Item findByID(@PathVariable Integer id){

        Optional<Item> item = itemRepository.findByID(id);
        if(item.isEmpty()){
            throw new ItemNotFoundException();
        }
        return item.get();
    }

    //post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Item item){
        itemRepository.create(item);
    }

    //put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Item item, @PathVariable Integer id){
        itemRepository.update(item, id);
    }

    //delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        itemRepository.delete(id);
    }*/
}
