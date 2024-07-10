package com.pustot.studling.controller;

import com.pustot.studling.dto.TrainingResultDTO;
import com.pustot.studling.service.ZhYueCanMasteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zh-yue-can-masteries")
public class ZhYueCanMasteryController {

    @Autowired
    private ZhYueCanMasteryService zhYueCanMasteryService;

    @PutMapping("/update")
    public void updateTrainingResults(@RequestBody List<TrainingResultDTO> results) {
        zhYueCanMasteryService.updateZhYueCanTrainingResults(results);
    }
}
