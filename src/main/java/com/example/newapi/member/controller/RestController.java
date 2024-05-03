package com.example.newapi.member.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class RestController {

    @ResponseBody
    @GetMapping("/apitest")
    public HashMap<String, Object> test(){
        HashMap<String, Object> paramap = new HashMap<>();
        paramap.put("1", "박진우");
        return paramap;
    }


}
