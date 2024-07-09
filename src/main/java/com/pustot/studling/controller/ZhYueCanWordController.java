package com.pustot.studling.controller;

import com.pustot.studling.model.ZhYueCanWord;
import com.pustot.studling.service.ZhYueCanWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zh-yue-can-words")
public class ZhYueCanWordController {

    @Autowired
    private ZhYueCanWordService zhYueCanWordService;

    @GetMapping("/random/{num}")
    public List<ZhYueCanWord> getRandomWords(@PathVariable int num) {
        // 限定 num 不超 50
        if (num > 50) {
            num = 50;
        }
        return zhYueCanWordService.getRandomWords(num);
    }
}
