package com.untilifoundyou.lostandfound.item;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class ItemController {

    @GetMapping("/hello")
    String home(){
        return "hahahahhalopit!";
    }

}
