package com.untilifoundyou.lostandfound.item;

import java.time.LocalDateTime;

public record Item (Integer itemID, String itemName, String itemDesc, String itemLoc, LocalDateTime postedOn, LocalDateTime timeOfConcern, ItemStatus Lost){}