package com.untilifoundyou.lostandfound.item;

import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public record Item (Integer itemID, @NotEmpty String itemName, String itemDesc, String itemLoc, LocalDateTime postedOn, LocalDateTime timeOfConcern, ItemStatus Lost){

}