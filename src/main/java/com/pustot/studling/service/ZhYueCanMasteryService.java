package com.pustot.studling.service;

import com.pustot.studling.dto.TrainingResultDTO;
import com.pustot.studling.model.ZhYueCanMastery;
import com.pustot.studling.repository.ZhYueCanMasteryMapper;
import com.pustot.studling.repository.DailyTrainingStatsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ZhYueCanMasteryService {

    @Autowired
    private ZhYueCanMasteryMapper zhYueCanMasteryMapper;

    @Autowired
    private DailyTrainingStatsMapper dailyTrainingStatsMapper;

    @Autowired
    private UserService userService;

    @Transactional
    public void updateZhYueCanTrainingResults(List<TrainingResultDTO> results) {
        for (TrainingResultDTO result : results) {
            int userId = userService.findByEmail(result.getUserEmail()).getUserId();
            ZhYueCanMastery currentMastery = zhYueCanMasteryMapper.findByUserIdAndWordId(userId, result.getWordId());

            String recentResults;
            float accuracy;

            if (currentMastery != null) {
                recentResults = updateRecentResults(currentMastery.getRecentResults(), result.isCorrect());
                accuracy = calculateAccuracy(recentResults);
                zhYueCanMasteryMapper.updateTrainingResult(userId, result.getWordId(), recentResults, accuracy);
            } else {
                recentResults = updateRecentResults("0000000000", result.isCorrect());
                accuracy = calculateAccuracy(recentResults);
                zhYueCanMasteryMapper.insertTrainingResult(userId, result.getWordId(), recentResults, accuracy);
            }
        }
    }

    private String updateRecentResults(String currentResults, boolean isCorrect) {
        // 将最新的结果添加到 recentResults 末尾
        String newResult = isCorrect ? "1" : "0";
        // 保持 recentResults 长度为 10
        if (currentResults.length() >= 10) {
            currentResults = currentResults.substring(1) + newResult;
        } else {
            currentResults += newResult;
        }
        System.out.println("Result: " + isCorrect + ". Updated recentResults: " + currentResults); // 添加日志记录
        return currentResults;
    }

    private float calculateAccuracy(String recentResults) {
        long correctCount = recentResults.chars().filter(ch -> ch == '1').count();
        return (float) correctCount / recentResults.length();
    }
}
