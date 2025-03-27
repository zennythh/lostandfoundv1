package com.untilifoundyou.lostandfound.repository;

import com.untilifoundyou.lostandfound.model.*;
import com.untilifoundyou.lostandfound.controller.*;
import org.springframework.stereotype.Repository;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.util.Assert;


@Repository
public class ItemRepository {

    private static final Logger log = LoggerFactory.getLogger(ItemRepository.class);
    private final JdbcClient jdbcClient;

    public ItemRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    
    // FIND ALL ITEMS
    public List<Item> findAll(){
        return jdbcClient.sql("SELECT * FROM Item")
        .query(Item.class)
        .list();
    }

    public Optional<Item> findByID(Integer itemid) {
    return jdbcClient.sql("SELECT item_id, name, description, location, reported_on, found_on, status, campus FROM Item WHERE item_id = ?")
        .params(List.of(itemid)) // Pass itemid as a parameter
        .query(Item.class)
        .optional();
}

    // FIND BY CAMPUS
    public Optional<Item> findByCampus(ItemCampus campus) {
        return jdbcClient.sql("SELECT * FROM Item WHERE campus = ?")
        .params(List.of(campus.toString()))
        .query(Item.class)
        .optional();
}

    // CREATE METHOD
     public void create(Item item) {
        var updated = jdbcClient.sql("INSERT INTO Item (item_id, name, description, location, reported_on, found_on, status, campus) values(?,?,?,?,?,?,?,?)")
        .params(List.of(item.item_id(), item.name(), item.description(), item.location(), item.reportedOn(), item.foundOn(), item.status().toString(), item.campus().toString()))
        .update();

        Assert.state(updated == 1, "Failed to create item " + item.name());
    }
    
    // UPDATE METHOD
    public void update (Item item, Integer itemid) {
        var updated = jdbcClient.sql("update item set name = ?, description = ?, location = ?, reported_on = ?, found_on = ?,  status = ?, campus = ? where item_id = ?")
        .params(List.of(item.item_id(), item.name(), item.description(), item.location(), item.reportedOn(), item.foundOn(), item.status().toString(), item.campus().toString(), itemid))
        .update();

        Assert.state(updated == 1, "Failed to update item " + item.name());
    }

    // DELETE METHOD
    public void delete (Integer itemid) {
        var updated = jdbcClient.sql("delete from item where item_id = :id")
        .param("itemid", itemid)
        .update();

        Assert.state(updated == 1, "Failed to delete item " + itemid);
    }

    // RETURN ITEM COUNT METHOD
    public int count(){
        return jdbcClient.sql("SELECT * FROM Item")
        .query()
        .listOfRows()
        .size();
    }

    // SAVE ALL ITEMS METHOD
    public void saveAll(List<Item> items) {
        items.stream().forEach(this::create);
    }
 
}


