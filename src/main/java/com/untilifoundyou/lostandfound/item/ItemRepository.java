package com.untilifoundyou.lostandfound.item;

import org.springframework.stereotype.Repository;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Repository
public class ItemRepository {

    private List<Item> items = new ArrayList<>();
    
    List<Run> findAll(){
        return items;
    }
    
    @PostConstruct
    private void init(){
        items.add(new Item(1, "panyo na blu", LocalDateTime.now(), LocalDateTime.now().minus(1, ChronoUnit.HOURS), ItemStatus.Lost));
        items.add(new Item(2, "selpon", LocalDateTime.now(), LocalDateTime.now().minus(3, ChronoUnit.HOURS), ItemStatus.Found));
    }
}
