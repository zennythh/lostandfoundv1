package com.untilifoundyou.lostandfound.item;

import java.time.LocalDateTime;

public record Item (String itemName, String itemDesc, String itemLoc, LocalDateTime postedOn, LocalDateTime timeOfConcern, ItemStatus Lost){}