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
    
    List<Item> findAll(){
        return items;
    }
    
    @PostConstruct
    private void init(){
        items.add(new Item(Integer.valueOf(1), "panyo na blu", "maliit lang po", "sa labas ng Room 301, Congress Campus", LocalDateTime.now(), LocalDateTime.now().minus(1, ChronoUnit.HOURS), ItemStatus.Lost));
        items.add(new Item(Integer.valueOf(2), "selpon", "tulfone", "sa F1 cr ng Silang Campus", LocalDateTime.now(), LocalDateTime.now().minus(3, ChronoUnit.HOURS), ItemStatus.Found));
    }
}
