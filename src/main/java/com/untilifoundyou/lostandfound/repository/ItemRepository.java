package com.untilifoundyou.lostandfound.repository;

import com.untilifoundyou.lostandfound.model.*;
import com.untilifoundyou.lostandfound.controller.*;
import com.untilifoundyou.lostandfound.enums.*;
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

    public static final Logger log = LoggerFactory.getLogger(ItemRepository.class);
    public final JdbcClient jdbcClient;

    public ItemRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    
    // FIND ALL ITEMS
    public List<Item> findAll(){
        return jdbcClient.sql("SELECT * FROM Item WHERE deleted = FALSE")
        .query(Item.class)
        .list();
    }

    // FIND ALL DELETED ITEMS
    public List<Item> findAllDeleted(){
        return jdbcClient.sql("SELECT * FROM Item WHERE deleted = TRUE")
        .query(Item.class)
        .list();
    }
    
    // FIND BY ID
    public Optional<Item> findByID(Integer itemid) {
    return jdbcClient.sql("SELECT * FROM Item WHERE item_id = ? AND deleted = FALSE")
        .params(List.of(itemid))
        .query(Item.class)
        .optional();
}

    // FIND BY CAMPUS
    public Optional<Item> findByCampus(ItemCampus campus) {
        return jdbcClient.sql("SELECT * FROM Item WHERE campus = ? AND deleted = FALSE")
        .params(List.of(campus.toString()))
        .query(Item.class)
        .optional();
}   

    // FIND BY STATUS
    public Optional<Item> findByStatus(ItemStatus status) {
        return jdbcClient.sql("SELECT * FROM Item WHERE status = ? AND deleted = FALSE")
        .params(status.toString())
        .query(Item.class)
        .optional();
}   

    // FIND BY CATEGORY
    public Optional<Item> findByCategory(ItemCategory category) {
        return jdbcClient.sql("SELECT * FROM Item WHERE category = ? AND deleted = FALSE")
        .params(category.toString())
        .query(Item.class)
        .optional();
}

    // CREATE METHOD
    public void create(Item item) {
        List<Object> params = new ArrayList<>();
        params.add(item.getName());
        params.add(item.getDescription());
        params.add(item.getLocation());
        params.add(item.getReportedOn());
        params.add(item.getFoundOn());
        params.add(item.getStatus() != null ? item.getStatus().toString() : null);
        params.add(item.getCampus() != null ? item.getCampus().toString() : null);
        params.add(item.getCategory() != null ? item.getCategory().toString() : null);
        var updated = jdbcClient.sql("INSERT INTO Item (name, description, location, reported_on, found_on, status, campus, category, deleted) values(?,?,?,?,?,?,?,?,FALSE)")
            .params(params)
            .update();

        Assert.state(updated == 1, "Failed to create item " + item.getName());
}

    // UPDATE METHOD
    public void update(Item item, Integer itemid) {
        List<Object> params = new ArrayList<>();
        params.add(item.getName());
        params.add(item.getDescription());
        params.add(item.getLocation());
        params.add(item.getReportedOn());
        params.add(item.getFoundOn());
        params.add(item.getStatus() != null ? item.getStatus().toString() : null);
        params.add(item.getCampus() != null ? item.getCampus().toString() : null);
        params.add(item.getCategory() != null ? item.getCategory().toString() : null);        
        params.add(itemid);

       var updated = jdbcClient.sql("UPDATE item SET name = ?, description = ?, location = ?, reported_on = ?, found_on = ?, status = ?, campus = ?, category = ? WHERE item_id = ?")
           .params(params)
           .update();

        Assert.state(updated == 1, "Failed to update item " + item.getName());
    }

    // DELETE METHOD
    public void delete(Integer itemId) {
        var updated = jdbcClient.sql("UPDATE item SET deleted = TRUE WHERE item_id = ?")
        .params(List.of(itemId)) 
        .update();

        Assert.state(updated == 1, "Failed to soft delete item " + itemId);
    }

    // RESTORE METHOD
    public void restore(Integer itemId) {
        var updated = jdbcClient.sql("UPDATE item SET deleted = FALSE WHERE item_id = ?")
        .params(List.of(itemId))
        .update();

        Assert.state(updated == 1, "Failed to restore the deleted item " + itemId);
    }

    // RETURN ITEM COUNT METHOD
    public int itemCount(){
        return jdbcClient.sql("SELECT * FROM Item WHERE deleted = FALSE")
        .query()
        .listOfRows()
        .size();
    }

    // RETURN ITEM COUNT BY STATUS
    public int itemCountByStatus(ItemStatus status){
        return jdbcClient.sql("SELECT * FROM Item WHERE status = ? AND deleted = FALSE")
        .params(status.toString())
        .query()
        .listOfRows()
        .size();
        }
    
    // SAVE ALL ITEMS METHOD
    public void saveAll(List<Item> items) {
        items.stream().forEach(this::create);
    }
}


