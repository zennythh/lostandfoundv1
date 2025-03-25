package com.untilifoundyou.lostandfound.repository;

import com.untilifoundyou.lostandfound.model.*;
import org.springframework.stereotype.Repository;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Repository
public class ItemRepository {

    private List<Item> items = new ArrayList<>();
    
    public List<Item> findAll(){
        return items;
    }
    
    public Optional<Item> findByID(Integer itemID){
        return items.stream()
        .filter(item -> item.itemID()== itemID)
        .findFirst();
    }

    public void create(Item item){
        items.add(item);
    }

    public void update(Item item, Integer id){
        Optional<Item> existingItem = findByID(id);
        if(existingItem.isPresent()){
            items.set(items.indexOf(existingItem.get()), item);
        }    
    }
    
    public void delete(Integer id) {
        items.removeIf(item -> item.itemID().equals(id));
    }

    @PostConstruct
    private void init(){
        items.add(new Item(1, "panyo na blu", "maikli", "congress highway", LocalDateTime.now(), LocalDateTime.now().minus(1, ChronoUnit.HOURS), ItemStatus.Lost));
        items.add(new Item(2, "selpon", "tulfone", "bagong silang phase 1", LocalDateTime.now(), LocalDateTime.now().minus(3, ChronoUnit.HOURS), ItemStatus.Found));
    }
}
