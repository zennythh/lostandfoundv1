package com.untilifoundyou.lostandfound.model;

import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public record Item(
    Integer itemID,
    @NotEmpty
    String name,
    String description,
    String location, 
    LocalDateTime foundDate,
    LocalDateTime reportedDate,
    ItemStatus status,
    ItemCampus campus
) {}