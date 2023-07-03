package com.num.shop.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("other")
public class OtherController {
    @RequestMapping("message/{xxx}")
    public Map message(@PathVariable String xxx){
        Map map = new HashMap();
        map.put("message", xxx);
        return map;
    }
}
