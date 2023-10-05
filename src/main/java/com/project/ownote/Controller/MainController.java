package com.project.ownote.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(){
        return "ownote";
    }

    @GetMapping("/ownote")
    public String ownote() {
        return "ownote";
    }
}
