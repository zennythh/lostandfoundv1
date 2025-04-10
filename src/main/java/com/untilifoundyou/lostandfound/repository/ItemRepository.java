package com.untilifoundyou.lostandfound.repository;

import com.untilifoundyou.lostandfound.model.*;
import com.untilifoundyou.lostandfound.enums.*;
import com.untilifoundyou.lostandfound.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Repository
public class ItemRepository {

    private final JdbcClient jdbcClient;

    public ItemRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Autowired
    private JwtUtil jwtUtil;


    // FIND ALL ITEMS
    public List<Item> findAll() {
        return jdbcClient.sql("SELECT * FROM Item WHERE deleted = FALSE")
                .query(Item.class)
                .list();
    }

    // FIND ITEM BY ID
    public Optional<Item> findById(Long itemId) {
        return jdbcClient.sql("SELECT * FROM Item WHERE item_id = ? AND deleted = FALSE")
                .params(List.of(itemId))
                .query(Item.class)
                .optional();
    }

    // CREATE ITEM
    public void create(Item item, Long authorId) {
        List<Object> params = new ArrayList<>();
        params.add(item.getName());
        params.add(item.getDescription());
        params.add(item.getLocation());
        params.add(item.getReportedOn());
        params.add(item.getFoundOn());
        params.add(item.getStatus() != null ? item.getStatus().toString() : null);
        params.add(item.getCampus() != null ? item.getCampus().toString() : null);
        params.add(item.getCategory() != null ? item.getCategory().toString() : null);
        params.add(authorId); // Use the extracted authorId directly

        var updated = jdbcClient.sql(
                "INSERT INTO item (name, description, location, reported_on, found_on, status, campus, category, deleted, author_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, FALSE, ?)"
        ).params(params).update();

        Assert.state(updated == 1, "Failed to create item " + item.getName());

        Long generatedId = jdbcClient.sql("SELECT LAST_INSERT_ID()")
                .query(Long.class)
                .single();

        Item savedItem = jdbcClient.sql("SELECT * FROM item WHERE item_id = ?")
                .params(generatedId)
                .query(Item.class)
                .single();

        item.setItemId(generatedId);
        item.setAuthorId(savedItem.getAuthorId());
    }

    // UPDATE ITEM
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

    // DELETE ITEM
    public void delete(Integer itemId) {
        var updated = jdbcClient.sql("UPDATE item SET deleted = TRUE WHERE item_id = ?")
                .params(List.of(itemId))
                .update();

        Assert.state(updated == 1, "Failed to soft delete item " + itemId);
    }

    // RESTORE ITEM
    public void restore(Integer itemId) {
        var updated = jdbcClient.sql("UPDATE item SET deleted = FALSE WHERE item_id = ?")
                .params(List.of(itemId))
                .update();

        Assert.state(updated == 1, "Failed to restore the deleted item " + itemId);
    }

    // RETURN ITEM COUNT
    public int itemCount() {
        return jdbcClient.sql("SELECT * FROM Item WHERE deleted = FALSE")
                .query()
                .listOfRows()
                .size();
    }

    // RETURN ITEM COUNT BY STATUS
    public int itemCountByStatus(ItemStatus status) {
        return jdbcClient.sql("SELECT * FROM Item WHERE status = ? AND deleted = FALSE")
                .params(status.toString())
                .query()
                .listOfRows()
                .size();
    }
}
