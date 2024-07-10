package com.pustot.studling.controller;

import com.pustot.studling.dto.TrainingResultDTO;
import com.pustot.studling.model.DailyTrainingStats;
import com.pustot.studling.service.DailyTrainingStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/daily-training-stats")
public class DailyTrainingStatsController {

    @Autowired
    private DailyTrainingStatsService dailyTrainingStatsService;

    @PutMapping("/update")
    public void updateDailyStats(@RequestBody List<TrainingResultDTO> results) {
        dailyTrainingStatsService.updateDailyStats(results.getFirst().getUserEmail(), results.getFirst().getLanguageCode(), results.getFirst().isCorrect());
    }

    @GetMapping("/today")
    public DailyTrainingStats getTodayTrainingStats(@RequestParam String userEmail, @RequestParam String languageCode) {
        return dailyTrainingStatsService.getTodayTrainingStats(userEmail, languageCode);
    }
}
