package com.untilifoundyou.lostandfound.model;

import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public record Item (Integer itemID, @NotEmpty String itemName, String itemDesc, String itemLoc, ItemCampus Campus, LocalDateTime postedOn, LocalDateTime timeOfConcern, ItemStatus Status){
}