package com.hg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("spik")
public class toSkip {

    @RequestMapping("toLess")
    public String toLess(){
        return "Demo/Less";
    }

    @RequestMapping("toStore")
    public String toStore(){
        return "Demo/store";
    }
}
