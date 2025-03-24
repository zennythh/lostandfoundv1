package com.untilifoundyou.lostandfound.item;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
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
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody Item item){
        itemRepository.create(item);
    }

    //put
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody Item item, @PathVariable Integer id){
        itemRepository.update(item, id);
    }

    //delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id){
        itemRepository.delete(id);
    }

}
