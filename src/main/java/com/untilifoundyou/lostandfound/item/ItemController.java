package com.untilifoundyou.lostandfound.run;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class RunController {

    @GetMapping("/hi")
    String home(){
        return "Hello Niggers!";
    }

}
