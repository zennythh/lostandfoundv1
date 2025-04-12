package com.untilifoundyou.lostandfound.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemSummaryDTO {
    private int lost;
    private int found;
    private int claimed;
}
