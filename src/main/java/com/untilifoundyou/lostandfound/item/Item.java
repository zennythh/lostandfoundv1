package com.untilifoundyou.lostandfound.item;

import java.time.LocalDateTime;

public record Item (Integer itemID, String itemName, LocalDateTime postedOn, LocalDateTime timeOfConcern, ItemStatus Lost){}