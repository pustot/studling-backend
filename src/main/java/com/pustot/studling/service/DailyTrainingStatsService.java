package com.pustot.studling.service;

import com.pustot.studling.model.DailyTrainingStats;
import com.pustot.studling.repository.DailyTrainingStatsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DailyTrainingStatsService {

    @Autowired
    private DailyTrainingStatsMapper dailyTrainingStatsMapper;

    @Autowired
    private UserService userService;

    @Transactional
    public void updateDailyStats(String userEmail, String languageCode, boolean isCorrect) {
        int userId = userService.findByEmail(userEmail).getUserId();
        dailyTrainingStatsMapper.updateDailyStats(userId, languageCode, isCorrect);
    }

    @Transactional(readOnly = true)
    public DailyTrainingStats getTodayTrainingStats(String userEmail, String languageCode) {
        int userId = userService.findByEmail(userEmail).getUserId();
        return dailyTrainingStatsMapper.getTodayTrainingStats(userId, languageCode);
    }
}
