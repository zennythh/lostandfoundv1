package com.untilifoundyou.lostandfound.item;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }
    
    @GetMapping("")
    List<Item> findAll(){
        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    Item findByID(@PathVariable Integer id){

        Optional<Item> item = itemRepository.findByID(id);
        if(item.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return item.get();
    }

    //post
    void create(@RequestBody Item item){
        itemRepository.create(item);
    }

    //put

    //delete

}
